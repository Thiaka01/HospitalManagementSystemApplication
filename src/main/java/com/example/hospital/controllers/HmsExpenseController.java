package com.example.hospital.controllers;

import com.example.hospital.entities.Expense;
import com.example.hospital.entities.User;
import com.example.hospital.repositories.ExpenseRepository;
import com.example.hospital.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class HmsExpenseController {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public HmsExpenseController(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    private boolean allowed(Authentication auth) {
        User u = userRepository.findByUsername(auth.getName()).orElseThrow();
        return u.getRole() == User.Role.ADMIN || u.getRole() == User.Role.CASHIER;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> list(Authentication auth) {
        if (!allowed(auth)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(expenseRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Expense> create(Authentication auth, @RequestBody Expense expense) {
        if (!allowed(auth)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(expenseRepository.save(expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Authentication auth, @PathVariable int id) {
        if (!allowed(auth)) return ResponseEntity.status(403).build();
        expenseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
