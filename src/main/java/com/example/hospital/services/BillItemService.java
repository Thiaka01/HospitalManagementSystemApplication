package com.example.hospital.services;

import com.example.hospital.entities.BillItem;
import com.example.hospital.repositories.BillItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillItemService {

    private final BillItemRepository billItemRepo;

    public BillItemService(BillItemRepository billItemRepo) {
        this.billItemRepo = billItemRepo;
    }

    public BillItem save(BillItem billItem) {
        return billItemRepo.save(billItem);
    }

    public List<BillItem> getAll() {
        return billItemRepo.findAll();
    }

    public Optional<BillItem> getById(Integer id) {
        return billItemRepo.findById(id);
    }

    public void delete(Integer id) {
        billItemRepo.deleteById(id);
    }
}