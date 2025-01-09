package io.hardplastik.ds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.hardplastik.ds.data.CatExerciseRepository;
import io.hardplastik.ds.model.catalogs.CatExercise;

@Service
public class CatExerciseService {

    @Autowired
    private CatExerciseRepository catExerciseRepository;

    public List<CatExercise> getAllCatExercises() {
        return catExerciseRepository.findAll();
    }
    
}
