package com.example.hospital.repositories;

import com.example.hospital.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Example: find products by supplier
    // List<Product> findBySupplier(Supplier supplier);
}