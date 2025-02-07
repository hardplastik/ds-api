package io.hardplastik.ds.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.hardplastik.ds.model.Account;
import io.hardplastik.ds.model.ProgramSession;
import io.hardplastik.ds.model.ProgramSessionExercise;
import io.hardplastik.ds.model.ProgramSessionExerciseSet;
import io.hardplastik.ds.model.ProgramSessionTemplate;
import io.hardplastik.ds.model.ProgramSessionTemplateExercise;
import io.hardplastik.ds.model.ProgramSessionTemplateExerciseSet;
import io.hardplastik.ds.model.ProgramTemplate;
import io.hardplastik.ds.model.UserProgram;

@Service
public class UserProgramService {

    public UserProgram createProgramForUser(UUID accountId, ProgramTemplate programTemplate) {
        
        UserProgram userProgram = initializeUserProgram(accountId, programTemplate);

        programTemplate.getSessions()
        .forEach(templateSession -> {

            ProgramSession session = createProgramSession(userProgram, templateSession);

            templateSession.getExercises()
            .forEach(templateExercise -> {

                ProgramSessionExercise sessionExercise = createProgramSessionExercise(session, templateExercise);
                
                templateExercise.getSets()
                .forEach(templateSet -> {
                    ProgramSessionExerciseSet sessionExerciseSet = createProgramSessionExerciseSet(sessionExercise, templateSet);
                    sessionExercise.getSets().add(sessionExerciseSet);
                });

                session.getExercises().add(sessionExercise);
            });

            userProgram.getSessions().add(session);
        });

        return userProgram;

    }

    private UserProgram initializeUserProgram(UUID accountId, ProgramTemplate programTemplate) {

        UserProgram userProgram = new UserProgram();
        userProgram.setUser(new Account(accountId));
        userProgram.setEnrollDatetime(LocalDateTime.now());
        userProgram.setIsStarted(Boolean.FALSE);
        userProgram.setIsCompleted(Boolean.FALSE);
        userProgram.setSessions(new ArrayList<>());
        userProgram.setName(programTemplate.getName());
        userProgram.setWeeks(programTemplate.getWeeks());
        userProgram.setSessionsPerWeek(programTemplate.getSessionsPerWeek());
        return userProgram;

    }

    private ProgramSession createProgramSession(UserProgram userProgram, ProgramSessionTemplate templateSession) {

        ProgramSession session = new ProgramSession();
        session.setUserProgram(userProgram);
        session.setWeekNumber(templateSession.getWeekNumber());
        session.setWeekDay(templateSession.getWeekDay());
        session.setIsCompleted(Boolean.FALSE);
        session.setExercises(new ArrayList<>());
        session.setStartDatetime(null);
        session.setEndDatetime(null);
        return session;
    }

    private ProgramSessionExercise createProgramSessionExercise(ProgramSession session, ProgramSessionTemplateExercise templateExercise) {

        ProgramSessionExercise sessionExercise = new ProgramSessionExercise();
        sessionExercise.setSession(session);
        sessionExercise.setExercise(templateExercise.getExercise());
        sessionExercise.setOrderNumber(templateExercise.getOrderNumber());
        sessionExercise.setNotes(templateExercise.getNotes());
        sessionExercise.setIsCompleted(Boolean.FALSE);
        sessionExercise.setSets(new ArrayList<>());
        return sessionExercise;
    }
    

    private ProgramSessionExerciseSet createProgramSessionExerciseSet(ProgramSessionExercise sessionExercise, ProgramSessionTemplateExerciseSet templateSet) {

        ProgramSessionExerciseSet set = new ProgramSessionExerciseSet();
        set.setExercise(sessionExercise);
        set.setReps(null);
        set.setWeight(null);
        set.setTargetWeight(templateSet.getTargetWeight());
        set.setRpe(templateSet.getRpe());
        set.setUnit(templateSet.getUnit());
        set.setOrderNumber(templateSet.getOrderNumber());
        set.setIsCompleted(Boolean.FALSE);
        set.setMinReps(templateSet.getMinReps());
        set.setMaxReps(templateSet.getMaxReps());
        set.setRirMin(templateSet.getRirMin());
        set.setRirMax(templateSet.getRirMax());

        return set;

    }
}
