package com.example.hospital.config;

import com.example.hospital.entities.FacilitySettings;
import com.example.hospital.entities.User;
import com.example.hospital.repositories.FacilitySettingsRepository;
import com.example.hospital.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class DataSetup implements CommandLineRunner {

    private final UserService userService;
    private final FacilitySettingsRepository facilitySettingsRepository;

    public DataSetup(UserService userService, FacilitySettingsRepository facilitySettingsRepository) {
        this.userService = userService;
        this.facilitySettingsRepository = facilitySettingsRepository;
    }

    @Override
    public void run(String... args) {
        if (facilitySettingsRepository.findById(1).isEmpty()) {
            facilitySettingsRepository.save(FacilitySettings.builder()
                    .id(1)
                    .officialName("MediCore Hospital")
                    .phone("")
                    .county("")
                    .subcounty("")
                    .build());
        }

        ensureUser("admin", "admin123$", User.Role.ADMIN);
        ensureUser("cashier", "cashier123$", User.Role.CASHIER);
        ensureUser("clinician", "clinician123$", User.Role.CLINICIAN);
        ensureUser("lab", "lab123$", User.Role.LAB_TECHNICIAN);
    }

    private void ensureUser(String username, String rawPassword, User.Role role) {
        if (userService.findByUsername(username).isEmpty()) {
            User u = User.builder()
                    .username(username)
                    .passwordHash(rawPassword)
                    .role(role)
                    .status("ACTIVE")
                    .build();
            userService.saveUser(u);
            System.out.println("Seeded user: " + username + " / " + rawPassword);
        }
    }
}
