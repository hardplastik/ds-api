package io.hardplastik.ds.model;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.hardplastik.ds.model.enums.WeightUnit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "program_session_template_exercise_set")
@EqualsAndHashCode(of = "id")
public class ProgramSessionTemplateExerciseSet implements Serializable {

    @Id
    @UuidGenerator
    @Column(name = "program_session_template_exercise_set_id")
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_session_template_exercise_id")
    private ProgramSessionTemplateExercise exercise;

    @Column(name = "target_reps")
    private String targetReps;

    @Column(name = "target_weight")
    private Float targetWeight;

    private Integer rpe;

    @Enumerated(EnumType.STRING)
    private WeightUnit unit;

    @Column(name = "order_number")
    private Integer orderNumber;

}
