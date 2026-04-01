package com.example.hospital.services;

import com.example.hospital.entities.Service;
import com.example.hospital.repositories.ServiceRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class HospitalServiceService {

    private final ServiceRepository serviceRepo;

    public HospitalServiceService(ServiceRepository serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    // =========================
    // CREATE OR UPDATE SERVICE
    // =========================
    public Service saveService(Service service) {
        return serviceRepo.save(service);
    }

    // =========================
    // GET ALL SERVICES
    // =========================
    public List<Service> getAll() {
         return serviceRepo.findAll();
    }

    // =========================
    // GET SERVICE BY ID
    // =========================
    public Optional<Service> getById(Integer id) {
        return serviceRepo.findById(id);
    }

    // =========================
    // DELETE SERVICE
    // =========================
    public void delete(Integer id) {
        serviceRepo.deleteById(id);
    }

    public Service save(Service service) {
        return saveService(service);
    }
}