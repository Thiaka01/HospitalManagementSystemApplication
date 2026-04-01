package com.example.hospital.repositories;

import com.example.hospital.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query("SELECT COALESCE(SUM(e.amountPaid), 0) FROM Expense e WHERE e.createdAt BETWEEN :s AND :e")
    BigDecimal sumAmountBetween(@Param("s") LocalDateTime s, @Param("e") LocalDateTime e);
}
