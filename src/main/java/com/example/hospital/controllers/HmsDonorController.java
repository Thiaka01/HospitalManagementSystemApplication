package com.example.hospital.controllers;

import com.example.hospital.entities.DonorInsurer;
import com.example.hospital.entities.User;
import com.example.hospital.repositories.DonorInsurerRepository;
import com.example.hospital.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors-insurers")
public class HmsDonorController {

    private final DonorInsurerRepository repository;
    private final UserRepository userRepository;

    public HmsDonorController(DonorInsurerRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    private boolean allowed(Authentication auth) {
        User u = userRepository.findByUsername(auth.getName()).orElseThrow();
        return u.getRole() == User.Role.ADMIN || u.getRole() == User.Role.CASHIER;
    }

    @GetMapping
    public ResponseEntity<List<DonorInsurer>> list(Authentication auth) {
        if (!allowed(auth)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<DonorInsurer> save(Authentication auth, @RequestBody DonorInsurer entity) {
        if (!allowed(auth)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Authentication auth, @PathVariable int id) {
        if (!allowed(auth)) return ResponseEntity.status(403).build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
