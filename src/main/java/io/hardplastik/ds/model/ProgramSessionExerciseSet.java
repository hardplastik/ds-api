package io.hardplastik.ds.model;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
@Table(name = "program_session_exercise_set")
public class ProgramSessionExerciseSet {
    
    @Id
    @UuidGenerator
    @Column(name = "exercise_set_id")
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(
        value = {
            @JoinColumn(name = "program_session_id"),
            @JoinColumn(name = "exercise_id")
        }
    )
    private ProgramSessionExercise session;

    private Integer reps;

    @Column(name = "target_reps")
    private String targetReps;

    private Float weight;

    @Column(name = "target_weight")
    private String targetWeight;

    private Integer rpe;

    private String unit;

}
