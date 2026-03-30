package com.example.hospital.repositories;

import com.example.hospital.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {
    // Example: find visits by status
    // List<Visit> findByStatus(Visit.Status status);
}