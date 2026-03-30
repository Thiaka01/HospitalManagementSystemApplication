package com.example.hospital.repositories;

import com.example.hospital.entities.HospitalServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<HospitalServiceEntity, Integer> {
}
    // Additional query methods can be added here if needed
