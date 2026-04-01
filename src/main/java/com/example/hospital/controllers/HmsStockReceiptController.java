package com.example.hospital.controllers;

import com.example.hospital.entities.StockReceipt;
import com.example.hospital.entities.User;
import com.example.hospital.repositories.UserRepository;
import com.example.hospital.services.StockReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-receipts")
public class HmsStockReceiptController {

    private final StockReceiptService stockReceiptService;
    private final UserRepository userRepository;

    public HmsStockReceiptController(StockReceiptService stockReceiptService, UserRepository userRepository) {
        this.stockReceiptService = stockReceiptService;
        this.userRepository = userRepository;
    }

    private boolean admin(Authentication auth) {
        User u = userRepository.findByUsername(auth.getName()).orElseThrow();
        return u.getRole() == User.Role.ADMIN;
    }

    @GetMapping
    public ResponseEntity<List<StockReceipt>> list(Authentication auth) {
        if (!admin(auth)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(stockReceiptService.list());
    }

    @PostMapping
    public ResponseEntity<StockReceipt> create(Authentication auth, @RequestBody StockReceipt receipt) {
        if (!admin(auth)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(stockReceiptService.createReceipt(receipt));
    }
}
