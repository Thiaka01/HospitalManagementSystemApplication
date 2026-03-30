package com.example.hospital.services;

import com.example.hospital.entities.Visit;
import com.example.hospital.repositories.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository visitRepo;

    public VisitService(VisitRepository visitRepo) {
        this.visitRepo = visitRepo;
    }

    public Visit save(Visit visit) {
        return visitRepo.save(visit);
    }

    public List<Visit> getAll() {
        return visitRepo.findAll();
    }

    public Optional<Visit> getById(Integer id) {
        return visitRepo.findById(id);
    }

    public void delete(Integer id) {
        visitRepo.deleteById(id);
    }
}