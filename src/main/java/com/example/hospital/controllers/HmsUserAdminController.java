package com.example.hospital.controllers;

import com.example.hospital.entities.User;
import com.example.hospital.repositories.UserRepository;
import com.example.hospital.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class HmsUserAdminController {

    private final UserRepository userRepository;
    private final UserService userService;

    public HmsUserAdminController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    private boolean admin(Authentication auth) {
        User u = userRepository.findByUsername(auth.getName()).orElseThrow();
        return u.getRole() == User.Role.ADMIN;
    }

    @GetMapping
    public ResponseEntity<List<User>> list(Authentication auth) {
        if (!admin(auth)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(userRepository.findAll());
    }

    public record CreateUserBody(String username, String oneTimePassword, String role, Boolean active) {}

    @PostMapping
    public ResponseEntity<User> create(Authentication auth, @RequestBody CreateUserBody body) {
        if (!admin(auth)) return ResponseEntity.status(403).build();
        User.Role r = User.Role.valueOf(body.role().toUpperCase());
        boolean active = body.active() == null || body.active();
        return ResponseEntity.ok(userService.adminCreateUser(body.username(), body.oneTimePassword(), r, active));
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(Authentication auth, @PathVariable int id) {
        if (!admin(auth)) return ResponseEntity.status(403).build();
        userService.setUserActive(id, false);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<Void> activate(Authentication auth, @PathVariable int id) {
        if (!admin(auth)) return ResponseEntity.status(403).build();
        userService.setUserActive(id, true);
        return ResponseEntity.noContent().build();
    }

    public record ResetPasswordBody(String newPassword) {}

    @PostMapping("/{id}/reset-password")
    public ResponseEntity<Void> reset(Authentication auth, @PathVariable int id, @RequestBody ResetPasswordBody body) {
        if (!admin(auth)) return ResponseEntity.status(403).build();
        userService.adminResetPassword(id, body.newPassword());
        return ResponseEntity.noContent().build();
    }
}
