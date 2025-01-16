package io.hardplastik.ds.controller.command;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import io.hardplastik.ds.model.Account;
import io.hardplastik.ds.model.ProgramSession;
import io.hardplastik.ds.model.ProgramSessionExercise;
import io.hardplastik.ds.model.ProgramSessionExerciseSet;
import io.hardplastik.ds.model.UserProgram;
import io.hardplastik.ds.model.catalogs.CatExercise;
import io.hardplastik.ds.model.enums.WeightUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProgramCommand {

    private UUID accountId;

    private String name;

    private List<ProgramSessionCommand> sessions;

    public UserProgram toEntity() {

        UserProgram userProgram = new UserProgram();

        userProgram.setUser(new Account(accountId));
        userProgram.setName(name);
        userProgram.setEnrollDatetime(LocalDateTime.now());
        userProgram.setIsStarted(Boolean.FALSE);
        userProgram.setIsCompleted(Boolean.FALSE);
        
        Integer weeks = sessions.stream()
            .map(ProgramSessionCommand::getWeekNumber)
            .reduce(0, (a, b) -> a > b ? a : b);
        
        userProgram.setWeeks(weeks);

        Map<Integer, List<ProgramSessionCommand>> weeksSessions = sessions
            .stream()
            .collect(Collectors.groupingBy(ProgramSessionCommand::getWeekNumber));

        Integer sessionsPerWeek = weeksSessions
            .keySet()
            .stream()
            .reduce(0, (a, key) -> a > weeksSessions.get(key).size() ? a : weeksSessions.get(key).size());
        
        userProgram.setSessionsPerWeek(sessionsPerWeek);

        userProgram.setSessions(sessions
            .stream()
            .map(session -> session.toEntity(userProgram))
            .collect(Collectors.toList()));

        return userProgram;
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
            session.setIsCompleted(Boolean.FALSE);
            session.setExercises(exercises
                .stream()
                .map(exercise -> exercise.toEntity(session))
                .collect(Collectors.toList())
            );
            
            return session;
        }
        
    }

    @Getter
    @Setter
    public static class ProgramSessionExerciseCommand {

        private UUID exerciseId;

        private int orderNumber;

        private String notes;

        private List<ProgramSessionExerciseSetCommand> sets;

        public ProgramSessionExercise toEntity(ProgramSession session) {
            ProgramSessionExercise exercise = new ProgramSessionExercise();

            exercise.setSession(session);
            exercise.setExercise(new CatExercise(exerciseId));
            exercise.setOrderNumber(orderNumber);
            exercise.setNotes(notes);
            exercise.setIsCompleted(Boolean.FALSE);
            exercise.setSets(sets
                .stream()
                .map(set -> set.toEntity(exercise))
                .collect(Collectors.toList())
            );
            return exercise;
        }

    }

    @Getter
    @Setter
    public static class ProgramSessionExerciseSetCommand {

        private Integer reps;

        private String targetReps;

        private Float weight;

        private Float targetWeight;

        private Integer rpe;

        private WeightUnit unit;

        private Integer orderNumber;

        public ProgramSessionExerciseSet toEntity(ProgramSessionExercise exercise) {
            
            ProgramSessionExerciseSet set = new ProgramSessionExerciseSet();
            
            set.setExercise(exercise);
            set.setReps(reps);
            set.setTargetReps(targetReps);
            set.setWeight(weight);
            set.setTargetWeight(targetWeight);
            set.setRpe(rpe);
            set.setUnit(unit);
            set.setOrderNumber(orderNumber);
            set.setIsCompleted(Boolean.FALSE);
            
            return set;
        }

    }

    
}