package com.example.hospital.controllers;

import com.example.hospital.entities.User;
import com.example.hospital.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class HmsReportsController {

    private final UserRepository userRepository;

    public HmsReportsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, String>> summary(Authentication auth) {
        User u = userRepository.findByUsername(auth.getName()).orElseThrow();
        if (u.getRole() != User.Role.ADMIN) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(Map.of(
                "message", "Administrator reports module — extend with exports and analytics.",
                "status", "placeholder"
        ));
    }
}
