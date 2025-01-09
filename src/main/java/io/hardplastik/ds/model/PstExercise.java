package io.hardplastik.ds.model;

import java.io.Serializable;

import io.hardplastik.ds.model.Serializable.PstExercisePk;
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
@Table(name = "pst_exercise")
@EqualsAndHashCode(of = "pk")
public class PstExercise implements Serializable {

    @EmbeddedId
    private PstExercisePk pk;

    @ManyToOne
    @MapsId("programSessionTemplateId")
    @JoinColumn(name = "program_session_template_id", nullable = false)
    private ProgramSessionTemplate programSessionTemplate;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn(name = "exercise_id", nullable = false)
    private CatExercise exercise;

    @Column(nullable = false)
    private int orderNumber;

    @Column(length = 200)
    private String notes;
}
