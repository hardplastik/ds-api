package io.hardplastik.ds.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.hardplastik.ds.data.CatExerciseRepository;
import io.hardplastik.ds.model.catalogs.CatExercise;

@Service
public class CatExerciseService {

    @Autowired
    private CatExerciseRepository repository;
    
    public CatExercise getExerciseById(UUID id) {
        return repository.findById(id).orElse(null);
    }
    
}
