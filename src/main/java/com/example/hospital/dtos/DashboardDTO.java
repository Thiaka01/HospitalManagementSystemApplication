package com.example.hospital.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class DashboardDTO {
    private long newRegistrations;
    private int patientsServed;
    private BigDecimal totalRevenue;
    private BigDecimal serviceRevenue;
    private BigDecimal productRevenue;
    private BigDecimal totalExpenses;
    private BigDecimal profitLoss;
    private BigDecimal unpaidAmount;
    private BigDecimal inventoryValue;
    private double averageItemsPerCompletedVisit;

    public BigDecimal getAverageRevenuePerPatient() {
        if (patientsServed == 0) return BigDecimal.ZERO;
        return totalRevenue.divide(BigDecimal.valueOf(patientsServed), 2, RoundingMode.HALF_UP);
    }
}
