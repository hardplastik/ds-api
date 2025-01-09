package io.hardplastik.ds.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.hardplastik.ds.model.catalogs.CatExercise;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "program_session_exercise")
@EqualsAndHashCode(of = "id")
public class ProgramSessionExercise implements Serializable {

    @Id
    @UuidGenerator
    @Column(name = "program_session_exercise_id")
    private UUID id;

    @JsonUnwrapped
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id")
    private CatExercise exercise;

    @Column(name = "order_number")
    private int orderNumber;

    private String notes;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_session_exercise_id")
    private List<ProgramSessionExerciseSet> sets;
}
