package com.example.hospital.controllers;

import com.example.hospital.entities.HospitalServiceEntity;
import com.example.hospital.services.HospitalServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final HospitalServiceService hospitalService;

    public ServiceController(HospitalServiceService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping
    public ResponseEntity<HospitalServiceEntity> createService(@RequestBody HospitalServiceEntity service) {
        return ResponseEntity.ok(hospitalService.save(service));
    }

    @GetMapping
    public ResponseEntity<List<HospitalServiceEntity>> getAllServices() {
        return ResponseEntity.ok(hospitalService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<HospitalServiceEntity>> getServiceById(@PathVariable Integer id) {
        return ResponseEntity.ok(hospitalService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalServiceEntity> updateService(
            @PathVariable Integer id,
            @RequestBody HospitalServiceEntity service
    ) {
        service.setId(id);
        return ResponseEntity.ok(hospitalService.save(service));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Integer id) {
        hospitalService.delete(id);
        return ResponseEntity.ok("Service deleted successfully");
    }
}