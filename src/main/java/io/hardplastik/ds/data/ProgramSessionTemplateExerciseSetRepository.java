package io.hardplastik.ds.data;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.hardplastik.ds.model.ProgramSessionTemplateExerciseSet;

public interface ProgramSessionTemplateExerciseSetRepository extends JpaRepository<ProgramSessionTemplateExerciseSet, UUID> {
    
}
