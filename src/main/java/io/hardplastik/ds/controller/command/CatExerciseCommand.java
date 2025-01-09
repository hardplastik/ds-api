package io.hardplastik.ds.controller.command;

import io.hardplastik.ds.model.catalogs.CatExercise;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatExerciseCommand {
    
    private String name;

    private String description;

    private String mediaUrl;

    public CatExercise toEntity() {
        CatExercise exercise = new CatExercise();
        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setMediaUrl(mediaUrl);
        return exercise;
    }

}
