package io.hardplastik.ds.controller.command;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import io.hardplastik.ds.model.Account;
import io.hardplastik.ds.model.ProgramSession;
import io.hardplastik.ds.model.ProgramSessionExercise;
import io.hardplastik.ds.model.ProgramSessionExerciseSet;
import io.hardplastik.ds.model.UserProgram;
import io.hardplastik.ds.model.catalogs.CatExercise;
import io.hardplastik.ds.model.enums.Goal;
import io.hardplastik.ds.model.enums.Intensity;
import io.hardplastik.ds.model.enums.Methodology;
import io.hardplastik.ds.model.enums.Phase;
import io.hardplastik.ds.model.enums.WeightUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProgramCommand {

    private UUID accountId;
    private String name;
    private Phase phase;
    private Goal goal;
    private Methodology methodology;
    private Intensity intensity;
    private Integer highSeries;
    private Integer lowSeries;
    private Integer minReps;
    private Integer maxReps;
    private Integer rirMin;
    private Integer rirMax;
    private List<ProgramSessionCommand> sessions;

    public UserProgram toEntity() {
        UserProgram userProgram = new UserProgram();

        userProgram.setUser(new Account(accountId));
        userProgram.setName(name);
        userProgram.setEnrollDatetime(LocalDateTime.now());
        userProgram.setIsStarted(false);
        userProgram.setIsCompleted(false);

        userProgram.setPhase(phase);
        userProgram.setGoal(goal);
        userProgram.setMethodology(methodology);
        userProgram.setIntensity(intensity);
        userProgram.setHighSeries(highSeries);
        userProgram.setLowSeries(lowSeries);

        calculateMinMaxRepsAndRir(userProgram);
        userProgram.setMinReps(minReps);
        userProgram.setMaxReps(maxReps);
        userProgram.setRirMin(rirMin);
        userProgram.setRirMax(rirMax);

        int weeks = sessions.stream().mapToInt(ProgramSessionCommand::getWeekNumber).max().orElse(0);
        userProgram.setWeeks(weeks);

        int sessionsPerWeek = sessions.stream()
            .collect(Collectors.groupingBy(ProgramSessionCommand::getWeekNumber, Collectors.counting()))
            .values().stream()
            .mapToInt(Long::intValue)
            .max()
            .orElse(0);
        userProgram.setSessionsPerWeek(sessionsPerWeek);

        userProgram.setSessions(
            sessions.stream().map(session -> session.toEntity(userProgram)).collect(Collectors.toSet())
        );

        return userProgram;
    }

    private void calculateMinMaxRepsAndRir(UserProgram userProgram) {
        int minRepsCalculated = Integer.MAX_VALUE;
        int maxRepsCalculated = Integer.MIN_VALUE;
        int rirMinCalculated = Integer.MAX_VALUE;
        int rirMaxCalculated = Integer.MIN_VALUE;

        for (ProgramSessionCommand sessionCommand : sessions) {
            for (ProgramSessionExerciseCommand exerciseCommand : sessionCommand.getExercises()) {
                for (ProgramSessionExerciseSetCommand setCommand : exerciseCommand.getSets()) {
                    minRepsCalculated = Math.min(minRepsCalculated, setCommand.getMinReps());
                    maxRepsCalculated = Math.max(maxRepsCalculated, setCommand.getMaxReps());
                    rirMinCalculated = Math.min(rirMinCalculated, setCommand.getRirMin());
                    rirMaxCalculated = Math.max(rirMaxCalculated, setCommand.getRirMax());
                }
            }
        }

        this.minReps = (minRepsCalculated == Integer.MAX_VALUE) ? null : minRepsCalculated;
        this.maxReps = (maxRepsCalculated == Integer.MIN_VALUE) ? null : maxRepsCalculated;
        this.rirMin = (rirMinCalculated == Integer.MAX_VALUE) ? null : rirMinCalculated;
        this.rirMax = (rirMaxCalculated == Integer.MIN_VALUE) ? null : rirMaxCalculated;
    }

    @Getter
    @Setter
    public static class ProgramSessionCommand {

        private Integer weekNumber;
        private Integer weekDay;
        private List<ProgramSessionExerciseCommand> exercises;

        public ProgramSession toEntity(UserProgram userProgram) {
            ProgramSession session = new ProgramSession();

            session.setUserProgram(userProgram);
            session.setWeekNumber(weekNumber);
            session.setWeekDay(weekDay);
            session.setIsCompleted(false);

            if (exercises != null) {
                session.setExercises(
                    exercises.stream().map(exercise -> exercise.toEntity(session)).collect(Collectors.toSet())
                );
            }

            return session;
        }
    }

    @Getter
    @Setter
    public static class ProgramSessionExerciseCommand {

        private UUID id;
        private int orderNumber;
        private String notes;
        private List<ProgramSessionExerciseSetCommand> sets;

        public ProgramSessionExercise toEntity(ProgramSession session) {
            ProgramSessionExercise exercise = new ProgramSessionExercise();

            exercise.setSession(session);
            exercise.setExercise(new CatExercise(id));
            exercise.setOrderNumber(orderNumber);
            exercise.setNotes(notes);
            exercise.setIsCompleted(false);

            if (sets != null) {
                exercise.setSets(
                    sets.stream().map(set -> set.toEntity(exercise)).collect(Collectors.toSet())
                );
            }

            return exercise;
        }
    }

    @Getter
    @Setter
    public static class ProgramSessionExerciseSetCommand {

        private Float targetWeight;
        private Integer minReps;
        private Integer maxReps;
        private Integer rirMin;
        private Integer rirMax;
        private Integer rpe;
        private WeightUnit unit;
        private Integer orderNumber;

        public ProgramSessionExerciseSet toEntity(ProgramSessionExercise exercise) {
            ProgramSessionExerciseSet set = new ProgramSessionExerciseSet();

            set.setExercise(exercise);
            set.setTargetWeight(targetWeight);
            set.setRpe(rpe);
            set.setUnit(unit);
            set.setOrderNumber(orderNumber);
            set.setIsCompleted(false);
            set.setMinReps(minReps);
            set.setMaxReps(maxReps);
            set.setRirMin(rirMin);
            set.setRirMax(rirMax);

            return set;
        }
    }
}
