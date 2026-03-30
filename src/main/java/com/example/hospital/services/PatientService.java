package com.example.hospital.services;


import com.example.hospital.entities.Patient;
import com.example.hospital.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepo;

    public PatientService(PatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }

    // Create or update a patient
    public Patient savePatient(Patient patient) {
        return patientRepo.save(patient);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    // Get patient by ID
    public Optional<Patient> getPatientById(Integer id) {
        return patientRepo.findById(id);
    }

    // Delete patient
    public void deletePatient(Integer id) {
        patientRepo.deleteById(id);
    }
}