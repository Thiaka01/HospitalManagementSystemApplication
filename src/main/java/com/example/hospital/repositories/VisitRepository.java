package com.example.hospital.repositories;

import com.example.hospital.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {

    List<Visit> findByStatusOrderByVisitDateDesc(Visit.Status status);

    List<Visit> findAllByOrderByVisitDateDesc();

    long countByStatusAndVisitDateBetween(Visit.Status status, java.time.LocalDateTime start, java.time.LocalDateTime end);
}
