package com.example.hospital.repositories;

import com.example.hospital.entities.DonorInsurer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorInsurerRepository extends JpaRepository<DonorInsurer, Integer> {

    List<DonorInsurer> findByNameContainingIgnoreCaseOrderByNameAsc(String q);

    List<DonorInsurer> findByActiveOrderByNameAsc(boolean active);
}
