package com.example.hospital.services;

import com.example.hospital.entities.HospitalServiceEntity;
import com.example.hospital.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceService {

    private final ServiceRepository serviceRepo;

    public HospitalServiceService(ServiceRepository serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    // =========================
    // CREATE OR UPDATE SERVICE
    // =========================
    public HospitalServiceEntity save(HospitalServiceEntity service) {
        return serviceRepo.save(service);
    }

    // =========================
    // GET ALL SERVICES
    // =========================
    public List<HospitalServiceEntity> getAll() {
        return serviceRepo.findAll();
    }

    // =========================
    // GET SERVICE BY ID
    // =========================
    public Optional<HospitalServiceEntity> getById(Integer id) {
        return serviceRepo.findById(id);
    }

    // =========================
    // DELETE SERVICE
    // =========================
    public void delete(Integer id) {
        serviceRepo.deleteById(id);
    }
}