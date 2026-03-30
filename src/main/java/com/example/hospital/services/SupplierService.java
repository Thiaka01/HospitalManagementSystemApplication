package com.example.hospital.services;

import com.example.hospital.entities.Supplier;
import com.example.hospital.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepo;

    public SupplierService(SupplierRepository supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    // Create or update a supplier
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    // Get all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    // Get supplier by ID
    public Optional<Supplier> getSupplierById(Integer id) {
        return supplierRepo.findById(id);
    }

    // Delete supplier
    public void deleteSupplier(Integer id) {
        supplierRepo.deleteById(id);
    }
}