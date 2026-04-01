package com.example.hospital.repositories;

import com.example.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("SELECT COUNT(p) FROM Patient p WHERE p.lastVisit BETWEEN :start AND :end")
    int countPatientsServed(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    long countByRegistrationDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT COALESCE(SUM(p.openBalance), 0) FROM Patient p")
    java.math.BigDecimal sumOpenBalances();
}