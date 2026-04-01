package com.example.hospital.controllers;

import com.example.hospital.entities.FacilitySettings;
import com.example.hospital.entities.User;
import com.example.hospital.repositories.FacilitySettingsRepository;
import com.example.hospital.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facility")
public class HmsFacilityController {

    private final FacilitySettingsRepository facilitySettingsRepository;
    private final UserRepository userRepository;

    public HmsFacilityController(FacilitySettingsRepository facilitySettingsRepository, UserRepository userRepository) {
        this.facilitySettingsRepository = facilitySettingsRepository;
        this.userRepository = userRepository;
    }

    private boolean admin(Authentication auth) {
        User u = userRepository.findByUsername(auth.getName()).orElseThrow();
        return u.getRole() == User.Role.ADMIN;
    }

    @GetMapping
    public ResponseEntity<FacilitySettings> get() {
        return ResponseEntity.ok(facilitySettingsRepository.findById(1).orElse(new FacilitySettings()));
    }

    @PutMapping
    public ResponseEntity<FacilitySettings> save(Authentication auth, @RequestBody FacilitySettings s) {
        if (!admin(auth)) return ResponseEntity.status(403).build();
        s.setId(1);
        return ResponseEntity.ok(facilitySettingsRepository.save(s));
    }
}
