package io.hardplastik.ds.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import io.hardplastik.ds.model.enums.Goal;
import io.hardplastik.ds.model.enums.Intensity;
import io.hardplastik.ds.model.enums.Methodology;
import io.hardplastik.ds.model.enums.Phase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@EqualsAndHashCode(of = "id")
@Table(name = "user_program")
public class UserProgram {

    @Id
    @UuidGenerator
    @Column(name = "user_program_id")
    private UUID id;

    private String name;

    @JsonIncludeProperties(value = {"id", "name", "lastName", "isTrainer"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id")
    private Account user;

    @Column(name = "enroll_datetime")
    private LocalDateTime enrollDatetime;

    @Column(name = "is_started")
    private Boolean isStarted;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    private Integer weeks;

    @Column(name = "sessions_per_week")
    private Integer sessionsPerWeek;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "phase")
    private Phase phase;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal")
    private Goal goal;

    @Enumerated(EnumType.STRING)
    @Column(name = "methodology")
    private Methodology methodology;

    @Enumerated(EnumType.STRING)
    @Column(name = "intensity")
    private Intensity intensity;

    @Column(name = "high_series")
    private Integer highSeries;

    @Column(name = "low_series")
    private Integer lowSeries;

    @Column(name = "min_reps")
    private Integer minReps;

    @Column(name = "max_reps")
    private Integer maxReps;

    @Column(name = "rir_min")
    private Integer rirMin;

    @Column(name = "rir_max")
    private Integer rirMax;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_program_id")
    private Set<ProgramSession> sessions;
}
