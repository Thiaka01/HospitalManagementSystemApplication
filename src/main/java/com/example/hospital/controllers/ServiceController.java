package com.example.hospital.controllers;

import com.example.hospital.entities.Service;
import com.example.hospital.services.HospitalServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    // Inject the Service Layer, not the Entity
    private final HospitalServiceService hospitalServiceService;

    public ServiceController(HospitalServiceService hospitalServiceService) {
        this.hospitalServiceService = hospitalServiceService;
    }

    @PostMapping
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        return ResponseEntity.ok(hospitalServiceService.save(service));
    }

    @GetMapping
    public ResponseEntity<List<Service>> getAllServices() {
        return ResponseEntity.ok(hospitalServiceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Service>> getServiceById(@PathVariable Integer id) {
        return ResponseEntity.ok(hospitalServiceService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(
            @PathVariable Integer id,
            @RequestBody Service service // Changed from HospitalService to Service
    ) {
        service.setId(id); // Use the setter that matches your Entity ID field
        return ResponseEntity.ok(hospitalServiceService.save(service));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Integer id) {
        hospitalServiceService.delete(id);
        return ResponseEntity.ok("Service deleted successfully");
    }
}