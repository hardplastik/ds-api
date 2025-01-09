package io.hardplastik.ds.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "program_session")
@EqualsAndHashCode(of = "id")
public class ProgramSession {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private UserProgram program;

    @Column(nullable = false)
    private Short weekNumber;

    @Column(nullable = false)
    private Short weekDay;

    @Column(nullable = false)
    private String startDatetime;

    @Column(nullable = false)
    private String endDatetime;
    
}
