package io.hardplastik.ds.model;

import java.io.Serializable;
import java.util.UUID;

import io.hardplastik.ds.model.Serializable.PstExerciseSetPk;
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
@Table(name = "pst_exercise_set")
@EqualsAndHashCode(of = "pk")
public class PstExerciseSet implements Serializable {

    @EmbeddedId
    private PstExerciseSetPk pk;

    @Column(name = "set_id", nullable = false)
    private UUID setId;

    @ManyToOne
    @MapsId("pstId")
    @JoinColumn(name = "pst_id", referencedColumnName = "program_session_template_id", nullable = false)
    private PstExercise pstId;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id", nullable = false)
    private CatExercise exerciseId;

    @Column(length = 20)
    private String targetReps;

    private Float targetWeight;

    private Short rpe;

    @Column(length = 1)
    private String unit;
}
