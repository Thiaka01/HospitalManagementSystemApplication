package com.example.hospital.services;

import com.example.hospital.entities.Product;
import com.example.hospital.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Optional<Product> getById(Integer id) {
        return productRepo.findById(id);
    }

    public void delete(Integer id) {
        productRepo.deleteById(id);
    }
}