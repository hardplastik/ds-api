package io.hardplastik.ds.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    @UuidGenerator
    @Column(name = "program_session_id")
    private UUID id;

    @Column(name= "program_id")
    private UUID programId;

    @Column(name = "week_number")
    private Short weekNumber;

    @Column(name = "week_day")
    private Short weekDay;

    @Column(name = "start_datetime")
    private String startDatetime;

    @Column(name = "end_datetime")
    private String endDatetime;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_session_id")
    private List<ProgramSessionExercise> exercises;
    
}
