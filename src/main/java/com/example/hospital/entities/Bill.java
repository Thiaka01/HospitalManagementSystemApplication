package com.example.hospital.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_id", unique = true, nullable = false)
    private Visit visit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier_id")
    private User cashier;

    @Column(nullable = false)
    private LocalDateTime billDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.DRAFTED;

    @Column(nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    public enum Status {
        DRAFTED,
        COMPLETED
    }
}