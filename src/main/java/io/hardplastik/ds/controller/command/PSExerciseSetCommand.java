package io.hardplastik.ds.controller.command;

import java.util.UUID;

import io.hardplastik.ds.model.enums.WeightUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PSExerciseSetCommand {

    private UUID psExerciseId;

    private String targetReps;

    private Float targetWeight;

    private WeightUnit unit;

}
