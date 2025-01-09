package io.hardplastik.ds.data;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.hardplastik.ds.model.ProgramSessionTemplateExercise;

public interface ProgramSessionTemplateExerciseRepository extends JpaRepository<ProgramSessionTemplateExercise, UUID> {
    
}
