package io.hardplastik.ds.controller;

import io.hardplastik.ds.controller.command.PSExerciseSetCommand;
import io.hardplastik.ds.controller.command.PSExerciseSetComplent;
import io.hardplastik.ds.controller.error.BusinessLogicException;
import io.hardplastik.ds.data.ProgramSessionExerciseSetRepository;
import io.hardplastik.ds.model.ProgramSessionExerciseSet;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/program-session-exercise-set")
public class ProgramSessionExerciseSetController {

    @Autowired
    private ProgramSessionExerciseSetRepository programSessionExerciseSetRepository;

    @GetMapping("/{id}")
    public ProgramSessionExerciseSet getProgramSessionExerciseSetById(@PathVariable UUID id) {
        return programSessionExerciseSetRepository.findById(id)
            .orElseThrow(() -> new BusinessLogicException("Program Session Exercise Set not found", HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PostMapping("")
    public ProgramSessionExerciseSet createProgramSessionExerciseSet(@RequestBody PSExerciseSetCommand command) {
        List<ProgramSessionExerciseSet> programSessionExerciseSets = programSessionExerciseSetRepository
                .findByExerciseId(command.getPsExerciseId());

        ProgramSessionExerciseSet programSessionExerciseSet = new ProgramSessionExerciseSet();

        programSessionExerciseSet.setExercise(programSessionExerciseSets.get(0).getExercise());
        programSessionExerciseSet.setReps(null);
        programSessionExerciseSet.setTargetReps(command.getTargetReps());
        programSessionExerciseSet.setWeight(null);
        programSessionExerciseSet.setTargetWeight(command.getTargetWeight());
        programSessionExerciseSet.setRpe(null);
        programSessionExerciseSet.setUnit(command.getUnit());
        programSessionExerciseSet.setOrderNumber(programSessionExerciseSets.size() + 1);
        programSessionExerciseSet.setPsesStatus(false);

        return programSessionExerciseSetRepository.save(programSessionExerciseSet);
    }

    @Transactional
    @PostMapping("/complete")
    public ProgramSessionExerciseSet completeProgramSessionExerciseSet(@RequestBody PSExerciseSetComplent command) {
        ProgramSessionExerciseSet programSessionExerciseSet = programSessionExerciseSetRepository
                .findById(command.getExerciseSetId()).orElse(null);

        programSessionExerciseSet.setReps(command.getReps());
        programSessionExerciseSet.setWeight(command.getWeight());
        programSessionExerciseSet.setRpe(command.getRpe());
        programSessionExerciseSet.setPsesStatus(true);

        return programSessionExerciseSetRepository.save(programSessionExerciseSet);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteProgramSessionExerciseSet(@PathVariable UUID id) {
        programSessionExerciseSetRepository.deleteById(id);
    }
}
