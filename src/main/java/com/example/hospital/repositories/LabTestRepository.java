package com.example.hospital.repositories;

import com.example.hospital.entities.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest, Integer> {

    List<LabTest> findByVisit_IdOrderByIdAsc(Integer visitId);
}
