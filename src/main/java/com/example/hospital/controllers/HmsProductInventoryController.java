package com.example.hospital.controllers;

import com.example.hospital.entities.Product;
import com.example.hospital.entities.User;
import com.example.hospital.repositories.ProductRepository;
import com.example.hospital.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory/products")
public class HmsProductInventoryController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public HmsProductInventoryController(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    private boolean admin(Authentication auth) {
        User u = userRepository.findByUsername(auth.getName()).orElseThrow();
        return u.getRole() == User.Role.ADMIN;
    }

    public record QuantityAdjustBody(int newQuantity, String reason) {}

    @PatchMapping("/{id}/quantity")
    public ResponseEntity<Product> adjust(@PathVariable int id, @RequestBody QuantityAdjustBody body, Authentication auth) {
        if (!admin(auth)) return ResponseEntity.status(403).build();
        Product p = productRepository.findById(id).orElseThrow();
        p.setQuantity(body.newQuantity());
        return ResponseEntity.ok(productRepository.save(p));
    }
}
