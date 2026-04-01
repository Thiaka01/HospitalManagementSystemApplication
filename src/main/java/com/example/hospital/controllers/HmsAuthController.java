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
@RequestMapping("/api/auth")
public class HmsAuthController {

    private final UserRepository userRepository;

    public HmsAuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> me(Authentication authentication) {
        User u = userRepository.findByUsername(authentication.getName()).orElseThrow();
        return ResponseEntity.ok(Map.of(
                "username", u.getUsername(),
                "role", u.getRole().name(),
                "status", u.getStatus(),
                "createdAt", u.getCreatedAt() != null ? u.getCreatedAt().toString() : "",
                "lastLoginAt", u.getLastLoginAt() != null ? u.getLastLoginAt().toString() : ""
        ));
    }
}
