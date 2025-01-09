package io.hardplastik.ds.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@EqualsAndHashCode(of = "id")
@Table(name = "program_session_template")
public class ProgramSessionTemplate {

    @Id
    @UuidGenerator
    @Column(name = "program_session_template_id")
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_template_id")
    private ProgramTemplate programTemplate;

    @Column(name = "week_number")
    private Integer weekNumber;

    @Column(name = "week_day")
    private Integer weekDay;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "program_session_template_id")
    private List<ProgramSessionTemplateExercise> exercises;
    
}
