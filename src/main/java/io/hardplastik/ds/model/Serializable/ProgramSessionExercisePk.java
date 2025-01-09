package io.hardplastik.ds.model.Serializable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramSessionExercisePk implements Serializable {
    
    private UUID programSessionId;
    private UUID exerciseId;

    public ProgramSessionExercisePk() {
    }

    public ProgramSessionExercisePk(UUID programSessionId, UUID exerciseId) {
        this.programSessionId = programSessionId;
        this.exerciseId = exerciseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramSessionExercisePk that = (ProgramSessionExercisePk) o;
        return Objects.equals(programSessionId, that.programSessionId) &&
                Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programSessionId, exerciseId);
    }
}