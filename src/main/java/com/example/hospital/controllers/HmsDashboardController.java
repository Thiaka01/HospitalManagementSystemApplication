package com.example.hospital.controllers;

import com.example.hospital.dtos.DashboardDTO;
import com.example.hospital.entities.User;
import com.example.hospital.repositories.UserRepository;
import com.example.hospital.services.DashboardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/dashboard")
public class HmsDashboardController {

    private final DashboardService dashboardService;
    private final UserRepository userRepository;

    public HmsDashboardController(DashboardService dashboardService, UserRepository userRepository) {
        this.dashboardService = dashboardService;
        this.userRepository = userRepository;
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardDTO> summary(
            Authentication auth,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        User u = userRepository.findByUsername(auth.getName()).orElseThrow();
        if (u.getRole() != User.Role.ADMIN) {
            return ResponseEntity.status(403).build();
        }

        LocalDate startDate = start != null ? start : LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = end != null ? end : LocalDate.now();
        return ResponseEntity.ok(dashboardService.getDashboardData(startDate, endDate));
    }
}
