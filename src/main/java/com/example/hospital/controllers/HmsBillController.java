package com.example.hospital.controllers;

import com.example.hospital.entities.Bill;
import com.example.hospital.entities.User;
import com.example.hospital.repositories.BillRepository;
import com.example.hospital.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bills")
public class HmsBillController {

    private final BillRepository billRepository;
    private final UserRepository userRepository;

    public HmsBillController(BillRepository billRepository, UserRepository userRepository) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }

    private boolean allowed(Authentication auth) {
        User u = userRepository.findByUsername(auth.getName()).orElseThrow();
        return u.getRole() == User.Role.ADMIN || u.getRole() == User.Role.CASHIER;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> list(
            Authentication auth,
            @RequestParam(defaultValue = "date") String sort) {
        if (!allowed(auth)) {
            return ResponseEntity.status(403).build();
        }
        List<Bill> bills = billRepository.findAll();
        if ("name".equalsIgnoreCase(sort)) {
            bills = bills.stream()
                    .sorted((a, b) -> a.getVisit().getPatient().getName().compareToIgnoreCase(b.getVisit().getPatient().getName()))
                    .collect(Collectors.toList());
        } else {
            bills = bills.stream()
                    .sorted((a, b) -> b.getBillDate().compareTo(a.getBillDate()))
                    .collect(Collectors.toList());
        }
        List<Map<String, Object>> rows = bills.stream().map(b -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", b.getId());
            m.put("visitId", b.getVisitId());
            m.put("patientName", b.getVisit().getPatient().getName());
            m.put("billTotal", b.getTotalAmount());
            m.put("status", b.getStatus().name());
            m.put("billDate", b.getBillDate() != null ? b.getBillDate().toString() : "");
            m.put("openBalance", b.getOpenBalance());
            m.put("totalPaid", b.getTotalPaid());
            return m;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(rows);
    }
}
