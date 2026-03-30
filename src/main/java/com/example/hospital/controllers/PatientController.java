package com.example.hospital.controllers;

import com.example.hospital.entities.Patient;
import com.example.hospital.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // =========================
    // CREATE PATIENT
    // =========================
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.savePatient(patient));
    }

    // =========================
    // GET ALL PATIENTS
    // =========================
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // =========================
    // GET PATIENT BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable Integer id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    // =========================
    // UPDATE PATIENT
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient patient) {
        patient.setPatientId(id);
        return ResponseEntity.ok(patientService.savePatient(patient));
    }

    // =========================
    // DELETE PATIENT
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }
}