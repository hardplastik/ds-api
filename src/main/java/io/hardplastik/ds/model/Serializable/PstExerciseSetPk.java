package io.hardplastik.ds.model.Serializable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PstExerciseSetPk implements Serializable {
    
    private UUID setId;
    private UUID pstId;
    private UUID exerciseId;

    public PstExerciseSetPk() {
    }

    public PstExerciseSetPk(UUID setId, UUID pstId, UUID exerciseId) {
        this.setId = setId;
        this.pstId = pstId;
        this.exerciseId = exerciseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PstExerciseSetPk that = (PstExerciseSetPk) o;
        return Objects.equals(setId, that.setId) &&
                Objects.equals(pstId, that.pstId) &&
                Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setId, pstId, exerciseId);
    }
}