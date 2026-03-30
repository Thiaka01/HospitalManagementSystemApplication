package com.example.hospital.controllers;

import com.example.hospital.entities.Supplier;
import com.example.hospital.services.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // =========================
    // CREATE SUPPLIER
    // =========================
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        return ResponseEntity.ok(supplierService.saveSupplier(supplier));
    }

    // =========================
    // GET ALL SUPPLIERS
    // =========================
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    // =========================
    // GET SUPPLIER BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Supplier>> getSupplierById(@PathVariable Integer id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    // =========================
    // UPDATE SUPPLIER
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Integer id, @RequestBody Supplier supplier) {
        supplier.setSupplierId(id);
        return ResponseEntity.ok(supplierService.saveSupplier(supplier));
    }

    // =========================
    // DELETE SUPPLIER
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Integer id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Supplier deleted successfully");
    }
}