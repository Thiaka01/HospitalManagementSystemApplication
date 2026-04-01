package com.example.hospital.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "facility_settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilitySettings {

    @Id
    private Integer id = 1;

    @Column(name = "official_name")
    private String officialName;

    private String phone;

    private String county;

    private String subcounty;

    @Column(name = "logo_url", length = 1024)
    private String logoUrl;
}
