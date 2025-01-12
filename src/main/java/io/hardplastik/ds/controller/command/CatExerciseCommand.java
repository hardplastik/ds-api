package io.hardplastik.ds.controller.command;

import java.util.ArrayList;
import java.util.List;

import io.hardplastik.ds.model.catalogs.CatExercise;
import io.hardplastik.ds.model.catalogs.CatExerciseMuscleGroup;
import io.hardplastik.ds.model.catalogs.CatMuscleGroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatExerciseCommand {
    
    private String name;

    private String description;

    private String url;

    private List<String> group;

    private List<String> subGroup;

    public CatExercise toEntity() {
        CatExercise exercise = new CatExercise();
        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setMediaUrl(url);

        List<CatExerciseMuscleGroup> groups = new ArrayList<>();

        group
            .stream()
            .map(g -> {
                CatExerciseMuscleGroup exerciseGroup = new CatExerciseMuscleGroup();
                exerciseGroup.setExercise(exercise);
                exerciseGroup.setIsPrincipal(Boolean.TRUE);
                exerciseGroup.setGroup(new CatMuscleGroup());
                exerciseGroup.getGroup().setName(g);
                return exerciseGroup;
            }).forEach(e -> groups.add(e));

        subGroup
            .stream()
            .map(g -> {
                CatExerciseMuscleGroup exerciseGroup = new CatExerciseMuscleGroup();
                exerciseGroup.setExercise(exercise);
                exerciseGroup.setIsPrincipal(Boolean.FALSE);
                exerciseGroup.setGroup(new CatMuscleGroup());
                exerciseGroup.getGroup().setName(g);
                return exerciseGroup;
            }).forEach(e -> groups.add(e));

        exercise.setGroups(groups);
        
        return exercise;
    }

}
