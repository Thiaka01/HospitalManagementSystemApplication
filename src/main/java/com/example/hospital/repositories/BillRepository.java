package com.example.hospital.repositories;

import com.example.hospital.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    Optional<Bill> findByVisit_Id(Integer visitId);

    @Query("SELECT COALESCE(SUM(b.totalAmount), 0) FROM Bill b WHERE b.billDate BETWEEN :start AND :end")
    BigDecimal sumAllTotalsBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COALESCE(SUM(b.totalAmount), 0) FROM Bill b WHERE b.itemType = :itemType AND b.billDate BETWEEN :start AND :end")
    BigDecimal sumByItemTypeBetween(@Param("itemType") String itemType,
                                    @Param("start") LocalDateTime start,
                                    @Param("end") LocalDateTime end);

    @Query("SELECT COALESCE(SUM(b.openBalance), 0) FROM Bill b WHERE b.openBalance > 0")
    BigDecimal sumUnpaidOpenBalances();

    @Query("SELECT COALESCE(SUM(b.openBalance), 0) FROM Bill b JOIN b.visit v WHERE v.patient.id = :pid")
    BigDecimal sumOpenBalanceForPatient(@Param("pid") Integer patientId);
}
