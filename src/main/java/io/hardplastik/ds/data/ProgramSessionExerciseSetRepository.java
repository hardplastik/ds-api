package io.hardplastik.ds.data;

import io.hardplastik.ds.model.ProgramSessionExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProgramSessionExerciseSetRepository extends JpaRepository<ProgramSessionExerciseSet, UUID> {
    List<ProgramSessionExerciseSet> findByExerciseId(UUID exerciseId);
}
