package com.example.hospital.repositories;

import com.example.hospital.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    // Example: find bills by status
    // List<Bill> findByStatus(Bill.Status status);
}