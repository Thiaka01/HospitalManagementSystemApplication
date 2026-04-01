package com.example.hospital.controllers;

import com.example.hospital.dtos.DashboardDTO;
import com.example.hospital.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String showDashboard(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            Model model) {

        // Default to current month if dates are missing
        LocalDate startDate = (start != null) ? start : LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = (end != null) ? end : LocalDate.now();

        DashboardDTO dashboard = dashboardService.getDashboardData(startDate, endDate);

        model.addAttribute("data", dashboard);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "admin_dashboard";
    }
}