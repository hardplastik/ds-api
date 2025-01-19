package io.hardplastik.ds.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.hardplastik.ds.model.catalogs.CatExercise;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @JsonIgnore
    @UuidGenerator
    @Column(name = "program_session_exercise_id")
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_session_id")
    private ProgramSession session;
    
    @JsonUnwrapped
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id")
    private CatExercise exercise;

    @Column(name = "order_number")
    private int orderNumber;

    @Column(name = "notes")
    private String notes;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "program_session_exercise_id")
    private List<ProgramSessionExerciseSet> sets;
}
