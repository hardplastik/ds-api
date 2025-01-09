package io.hardplastik.ds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.model.catalogs.CatExercise;
import io.hardplastik.ds.service.CatExerciseService;

@RestController
@RequestMapping("/api/exercise")
public class CatExerciseController {

    @Autowired
    private CatExerciseService catExerciseService;

    @GetMapping("")
    public List<CatExercise> getAllCatExercises() {
        return catExerciseService.getAllCatExercises();
    }
    
    
}
