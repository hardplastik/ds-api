package io.hardplastik.ds.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.catalogs.CatExercise;

@Repository
public interface CatExerciseRepository extends JpaRepository<CatExercise, String> {
    
}
