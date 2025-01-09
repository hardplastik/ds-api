package io.hardplastik.ds.model.Serializable;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"programSessionId", "exerciseId"})
public class ProgramSessionExercisePk implements Serializable {
    
    @Column(name = "program_session_id")
    private UUID programSessionId;

    @Column(name = "exercise_id")
    private UUID exerciseId;
    
}