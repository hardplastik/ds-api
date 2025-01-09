package io.hardplastik.ds.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.hardplastik.ds.model.Serializable.PstExercisePk;
import io.hardplastik.ds.model.catalogs.CatExercise;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "program_session_template_exercise")
@EqualsAndHashCode(of = "pk")
public class ProgramSessionTemplateExercise implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private PstExercisePk pk;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_session_template_id", insertable = false, updatable = false)
    private ProgramSessionTemplate programSessionTemplate;

    @JsonUnwrapped
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id", insertable = false, updatable = false)
    private CatExercise exercise;

    @Column(name = "order_number")
    private int orderNumber;

    private String notes;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "program_session_template_id"),
        @JoinColumn(name = "exercise_id")
    })
    private List<ProgramSessionTemplateExerciseSet> sets;
}
