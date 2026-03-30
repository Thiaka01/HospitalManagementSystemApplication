package com.example.hospital.repositories;

import com.example.hospital.entities.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillItemRepository extends JpaRepository<BillItem, Integer> {
    // Example: find all items for a specific bill
    // List<BillItem> findByBill(Bill bill);
}