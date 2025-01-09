package io.hardplastik.ds.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@EqualsAndHashCode(of = "id")
@Table(name = "program_session_template")
public class ProgramSessionTemplate {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "program_template_id", nullable = false)
    private ProgramTemplate programTemplate;

    @Column(nullable = false)
    private Short weekNumber;

    @Column(nullable = false)
    private Short weekDay;
    
}
