package com.example.hospital.repositories;

import com.example.hospital.entities.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillItemRepository extends JpaRepository<BillItem, Integer> {

    List<BillItem> findByBill_Id(Integer billId);

    @Query("SELECT COUNT(bi) FROM BillItem bi WHERE bi.bill.billDate BETWEEN :s AND :e")
    long countItemsBetween(@Param("s") LocalDateTime s, @Param("e") LocalDateTime e);
}
