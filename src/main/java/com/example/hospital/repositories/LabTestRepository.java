package com.example.hospital.repositories;

import com.example.hospital.entities.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest, Integer> {
    // Example: find tests by status
    // List<LabTest> findByStatus(LabTest.Status status);
}