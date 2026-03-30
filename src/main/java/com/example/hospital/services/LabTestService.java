package com.example.hospital.services;

import com.example.hospital.entities.LabTest;
import com.example.hospital.repositories.LabTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabTestService {

    private final LabTestRepository labTestRepo;

    public LabTestService(LabTestRepository labTestRepo) {
        this.labTestRepo = labTestRepo;
    }

    public LabTest save(LabTest labTest) {
        return labTestRepo.save(labTest);
    }

    public List<LabTest> getAll() {
        return labTestRepo.findAll();
    }

    public Optional<LabTest> getById(Integer id) {
        return labTestRepo.findById(id);
    }

    public void delete(Integer id) {
        labTestRepo.deleteById(id);
    }
}