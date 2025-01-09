package io.hardplastik.ds.model;

import java.io.Serializable;

import io.hardplastik.ds.model.Serializable.ProgramSessionExercisePk;
import io.hardplastik.ds.model.catalogs.CatExercise;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "program_session_exercise")
@EqualsAndHashCode(of = "pk")
public class ProgramSessionExercise implements Serializable {

    @EmbeddedId
    private ProgramSessionExercisePk pk;

    @ManyToOne
    @MapsId("programSessionId")
    @JoinColumn(name = "program_session_id", nullable = false)
    private ProgramSession programSession;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn(name = "exercise_id", nullable = false)
    private CatExercise exercise;

    @Column(nullable = false)
    private int orderNumber;

    @Column(length = 200)
    private String notes;
}
