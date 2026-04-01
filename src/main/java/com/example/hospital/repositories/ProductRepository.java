package com.example.hospital.repositories;

import com.example.hospital.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameContainingIgnoreCaseOrderByNameAsc(String q);

    @Query("SELECT COALESCE(SUM(p.quantity * COALESCE(p.lastBuyPrice, 0)), 0) FROM Product p")
    BigDecimal calculateTotalStockValue();
}
