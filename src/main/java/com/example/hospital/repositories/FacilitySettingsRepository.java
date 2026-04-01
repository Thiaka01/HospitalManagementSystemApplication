package com.example.hospital.repositories;

import com.example.hospital.entities.FacilitySettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilitySettingsRepository extends JpaRepository<FacilitySettings, Integer> {
}
