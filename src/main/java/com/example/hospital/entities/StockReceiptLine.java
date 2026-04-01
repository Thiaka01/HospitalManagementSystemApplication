package com.example.hospital.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "stock_receipt_lines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockReceiptLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id", nullable = false)
    private StockReceipt receipt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity_received", nullable = false)
    private Integer quantityReceived;

    @Column(name = "total_product_price", nullable = false, precision = 14, scale = 2)
    private BigDecimal totalProductPrice;

    @Column(name = "unit_buying_price", nullable = false, precision = 14, scale = 2)
    private BigDecimal unitBuyingPrice;
}
