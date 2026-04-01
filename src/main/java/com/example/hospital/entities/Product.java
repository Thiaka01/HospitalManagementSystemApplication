package com.example.hospital.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(precision = 14, scale = 2)
    private BigDecimal lastBuyPrice = BigDecimal.ZERO;

    @Column(precision = 14, scale = 2)
    private BigDecimal unitSellingPrice = BigDecimal.ZERO;

    private Integer reorderLevel = 0;

    private Integer quantity = 0;

    private LocalDate dateReceived;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private boolean inactive;

    private boolean expires;

    private LocalDate expiryDate;

    @PrePersist
    protected void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
