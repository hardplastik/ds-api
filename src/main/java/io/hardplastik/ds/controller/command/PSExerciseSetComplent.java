package io.hardplastik.ds.controller.command;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PSExerciseSetComplent {
    
    private UUID exerciseSetId;

    private Integer reps;

    private Float weight;

    private Integer rpe;

}
