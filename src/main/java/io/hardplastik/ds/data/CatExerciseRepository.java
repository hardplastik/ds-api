package io.hardplastik.ds.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.catalogs.CatExercise;

@Repository
public interface CatExerciseRepository extends JpaRepository<CatExercise, UUID> {

    @Query(value  = "select e from CatExercise e "
          + "join fetch e.groups eg "
          + "join fetch eg.group g"
    )
    @NonNull List<CatExercise> findAll();

}
