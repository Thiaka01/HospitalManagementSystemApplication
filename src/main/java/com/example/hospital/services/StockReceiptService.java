package com.example.hospital.services;

import com.example.hospital.entities.Product;
import com.example.hospital.entities.StockReceipt;
import com.example.hospital.entities.StockReceiptLine;
import com.example.hospital.entities.Supplier;
import com.example.hospital.repositories.ProductRepository;
import com.example.hospital.repositories.StockReceiptRepository;
import com.example.hospital.repositories.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class StockReceiptService {

    private final StockReceiptRepository receiptRepo;
    private final SupplierRepository supplierRepo;
    private final ProductRepository productRepo;

    public StockReceiptService(
            StockReceiptRepository receiptRepo,
            SupplierRepository supplierRepo,
            ProductRepository productRepo) {
        this.receiptRepo = receiptRepo;
        this.supplierRepo = supplierRepo;
        this.productRepo = productRepo;
    }

    public List<StockReceipt> list() {
        return receiptRepo.findAllByOrderByDateReceivedDesc();
    }

    @Transactional
    public StockReceipt createReceipt(StockReceipt receipt) {
        Supplier s = supplierRepo.findById(receipt.getSupplier().getId()).orElseThrow();
        receipt.setSupplier(s);
        for (StockReceiptLine line : receipt.getLines()) {
            Product p = productRepo.findById(line.getProduct().getId()).orElseThrow();
            line.setProduct(p);
            line.setReceipt(receipt);
            if ((line.getUnitBuyingPrice() == null || line.getUnitBuyingPrice().signum() == 0)
                    && line.getTotalProductPrice() != null && line.getQuantityReceived() != null) {
                line.setUnitBuyingPrice(unitPrice(line.getTotalProductPrice(), line.getQuantityReceived()));
            }
        }
        StockReceipt saved = receiptRepo.save(receipt);
        applyInventory(saved);
        return receiptRepo.findById(saved.getId()).orElseThrow();
    }

    private void applyInventory(StockReceipt receipt) {
        BigDecimal billSum = BigDecimal.ZERO;
        for (StockReceiptLine line : receipt.getLines()) {
            Product p = line.getProduct();
            int add = line.getQuantityReceived();
            p.setQuantity((p.getQuantity() != null ? p.getQuantity() : 0) + add);
            p.setLastBuyPrice(line.getUnitBuyingPrice());
            p.setDateReceived(receipt.getDateReceived());
            productRepo.save(p);
            billSum = billSum.add(line.getTotalProductPrice());
        }
        receipt.setBillTotal(billSum);
        receiptRepo.save(receipt);
    }

    public static BigDecimal unitPrice(BigDecimal totalPaid, int qty) {
        if (qty <= 0) return BigDecimal.ZERO;
        return totalPaid.divide(BigDecimal.valueOf(qty), 4, RoundingMode.HALF_UP);
    }
}
