package com.example.hospital.services;

import com.example.hospital.dtos.DashboardDTO;
import com.example.hospital.entities.Visit;
import com.example.hospital.repositories.BillItemRepository;
import com.example.hospital.repositories.BillRepository;
import com.example.hospital.repositories.ExpenseRepository;
import com.example.hospital.repositories.PatientRepository;
import com.example.hospital.repositories.ProductRepository;
import com.example.hospital.repositories.VisitRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class DashboardService {

    private final PatientRepository patientRepo;
    private final BillRepository billRepository;
    private final ExpenseRepository expenseRepository;
    private final ProductRepository productRepository;
    private final VisitRepository visitRepository;
    private final BillItemRepository billItemRepository;

    public DashboardService(
            PatientRepository patientRepo,
            BillRepository billRepository,
            ExpenseRepository expenseRepository,
            ProductRepository productRepository,
            VisitRepository visitRepository,
            BillItemRepository billItemRepository) {
        this.patientRepo = patientRepo;
        this.billRepository = billRepository;
        this.expenseRepository = expenseRepository;
        this.productRepository = productRepository;
        this.visitRepository = visitRepository;
        this.billItemRepository = billItemRepository;
    }

    public DashboardDTO getDashboardData(LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);

        DashboardDTO dto = new DashboardDTO();

        dto.setNewRegistrations(patientRepo.countByRegistrationDateBetween(start, end));
        dto.setPatientsServed(patientRepo.countPatientsServed(start, end));

        BigDecimal total = billRepository.sumAllTotalsBetween(start, end);
        BigDecimal service = billRepository.sumByItemTypeBetween("SERVICE", start, end);
        BigDecimal product = billRepository.sumByItemTypeBetween("PRODUCT", start, end);

        dto.setTotalRevenue(total != null ? total : BigDecimal.ZERO);
        dto.setServiceRevenue(service != null ? service : BigDecimal.ZERO);
        dto.setProductRevenue(product != null ? product : BigDecimal.ZERO);

        BigDecimal expenses = expenseRepository.sumAmountBetween(start, end);
        dto.setTotalExpenses(expenses != null ? expenses : BigDecimal.ZERO);
        dto.setProfitLoss(dto.getTotalRevenue().subtract(dto.getTotalExpenses()));

        BigDecimal unpaidBills = billRepository.sumUnpaidOpenBalances();
        dto.setUnpaidAmount(unpaidBills != null ? unpaidBills : BigDecimal.ZERO);

        BigDecimal inv = productRepository.calculateTotalStockValue();
        dto.setInventoryValue(inv != null ? inv : BigDecimal.ZERO);

        long completed = visitRepository.countByStatusAndVisitDateBetween(Visit.Status.COMPLETED, start, end);
        long items = billItemRepository.countItemsBetween(start, end);
        dto.setAverageItemsPerCompletedVisit(completed == 0 ? 0.0 : (double) items / completed);

        return dto;
    }
}
