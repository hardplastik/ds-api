package io.hardplastik.ds.model.Serializable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PstExercisePk implements Serializable {
    
    private UUID programSessionTemplateId;
    private UUID exerciseId;

    public PstExercisePk() {
    }

    public PstExercisePk(UUID programSessionTemplateId, UUID exerciseId) {
        this.programSessionTemplateId = programSessionTemplateId;
        this.exerciseId = exerciseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PstExercisePk that = (PstExercisePk) o;
        return Objects.equals(programSessionTemplateId, that.programSessionTemplateId) &&
                Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programSessionTemplateId, exerciseId);
    }
}