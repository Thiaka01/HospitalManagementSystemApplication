package com.example.hospital.services;

import com.example.hospital.entities.Bill;
import com.example.hospital.repositories.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository billRepo;

    public BillService(BillRepository billRepo) {
        this.billRepo = billRepo;
    }

    public Bill save(Bill bill) {
        return billRepo.save(bill);
    }

    public List<Bill> getAll() {
        return billRepo.findAll();
    }

    public Optional<Bill> getById(Integer id) {
        return billRepo.findById(id);
    }

    public void delete(Integer id) {
        billRepo.deleteById(id);
    }
}