package com.example.hospital.controllers;

import com.example.hospital.dto.LoginRequest;
import com.example.hospital.entities.User;
import com.example.hospital.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        try {
            User user = userService.authenticate(
                    request.getUsername(),
                    request.getPassword()
            );

            return ResponseEntity.ok(user);

        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}