package io.hardplastik.ds.controller.command;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import io.hardplastik.ds.model.ProgramSessionTemplate;
import io.hardplastik.ds.model.ProgramSessionTemplateExercise;
import io.hardplastik.ds.model.ProgramSessionTemplateExerciseSet;
import io.hardplastik.ds.model.ProgramTemplate;
import io.hardplastik.ds.model.WeightUnit;
import io.hardplastik.ds.model.catalogs.CatExercise;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramTemplateCommand {
    
    private String name;

    private String description;

    private List<ProgramSessionTemplateCommand> sessions;

    public ProgramTemplate toEntity() {
        ProgramTemplate template = new ProgramTemplate();
        template.setName(name);
        template.setDescription(description);
        template.setSessions(
            sessions.stream()
                .map(session -> session.toEntity(template))
                .collect(Collectors.toList())
        );
        return template;
    }

    @Getter
    @Setter
    public static class ProgramSessionTemplateCommand {
        
        private Integer weekNumber;

        private Integer weekDay;

        private List<ProgramSessionTemplateExerciseCommand> exercises;

        public ProgramSessionTemplate toEntity(ProgramTemplate template) {
            ProgramSessionTemplate session = new ProgramSessionTemplate();
            session.setProgramTemplate(template);
            session.setWeekNumber(weekNumber);
            session.setWeekDay(weekDay);
            session.setExercises(
                exercises.stream()
                    .map(exercise -> exercise.toEntity(session))
                    .collect(Collectors.toList())
            );
            return session;
        }

    }

    @Getter
    @Setter
    public static class ProgramSessionTemplateExerciseCommand {

        private UUID exerciseId;

        private Integer orderNumber;

        private String notes;

        private List<ProgramSessionTemplateSetCommand> sets;

        public ProgramSessionTemplateExercise toEntity(ProgramSessionTemplate session) {
            ProgramSessionTemplateExercise exercise = new ProgramSessionTemplateExercise();
            exercise.setSession(session);
            exercise.setExercise(new CatExercise(exerciseId));
            exercise.setOrderNumber(orderNumber);
            exercise.setNotes(notes);
            exercise.setSets(
                sets.stream()
                    .map(set -> set.toEntity(exercise))
                    .collect(Collectors.toList())
            );
            return exercise;
        }
        
    }

    @Getter
    @Setter
    public static class ProgramSessionTemplateSetCommand {

        private String targetReps;

        private Float targetWeight;

        private Integer rpe;

        private WeightUnit unit;

        private Integer orderNumber;

        public ProgramSessionTemplateExerciseSet toEntity(ProgramSessionTemplateExercise exercise) {
            ProgramSessionTemplateExerciseSet set = new ProgramSessionTemplateExerciseSet();
            set.setExercise(exercise);
            set.setTargetReps(targetReps);
            set.setTargetWeight(targetWeight);
            set.setRpe(rpe);
            set.setUnit(unit);
            set.setOrderNumber(orderNumber);
            return set;
        }

    }
    
}
