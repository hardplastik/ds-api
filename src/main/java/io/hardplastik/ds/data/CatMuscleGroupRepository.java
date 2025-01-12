package io.hardplastik.ds.data;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.hardplastik.ds.model.catalogs.CatMuscleGroup;

public interface CatMuscleGroupRepository extends JpaRepository<CatMuscleGroup, UUID> {
  
  Optional<CatMuscleGroup> findByName(String name);

}
