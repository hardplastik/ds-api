package io.hardplastik.ds.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.controller.command.CatExerciseCommand;
import io.hardplastik.ds.controller.error.NotFoundException;
import io.hardplastik.ds.data.CatExerciseRepository;
import io.hardplastik.ds.model.catalogs.CatExercise;

@RestController
@RequestMapping("/exercises")
public class CatExerciseController {

    @Autowired
    private CatExerciseRepository repository;

    @GetMapping("")
    public List<CatExercise> getAllCatExercises() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public CatExercise getExerciseById(@PathVariable UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Exercise not found"));
    }

    @PostMapping("")
    public CatExercise addExercise(@RequestBody CatExerciseCommand command) {
        CatExercise exercise = command.toEntity();
        return repository.save(exercise);
    }

}
