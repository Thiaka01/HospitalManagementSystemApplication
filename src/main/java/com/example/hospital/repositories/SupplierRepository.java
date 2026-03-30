package com.example.hospital.repositories;

import com.example.hospital.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    // Additional query methods can be added here if needed
}