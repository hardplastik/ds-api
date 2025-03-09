package io.hardplastik.ds.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.hardplastik.ds.data.ProgramSessionRepository;
import io.hardplastik.ds.data.UserProgramRepository;
import io.hardplastik.ds.model.ProgramSession;
import io.hardplastik.ds.model.UserProgram;
import io.hardplastik.ds.model.projections.ProgramSessionProjection;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgramSessionService {

    private final ProgramSessionRepository programSessionRepository;
    private final UserProgramRepository userProgramRepository;

    public Optional<ProgramSessionProjection> getCurrentSession(UUID userId) {
        Optional<UserProgram> userProgram = userProgramRepository.findTopByUserIdOrderByEnrollDatetimeDesc(userId);

        if (userProgram.isPresent()) {
            return programSessionRepository.findTopByUserProgramIdAndIsCompletedOrderByWeekNumberAscWeekDayAsc(userProgram.get().getId(), false);
        }
        return Optional.empty();
    }

    public List<ProgramSessionProjection> getLastCompletedSessions(UUID userId, Pageable pageable) {
        return programSessionRepository.findLastCompletedSessionsByUserId(userId, pageable);
    }

    public ProgramSession startSession(ProgramSession session) {
        session.setStartDatetime(LocalDateTime.now());
        return programSessionRepository.save(session);
    }

    public ProgramSession completeSession(ProgramSession session) {
        session.setEndDatetime(LocalDateTime.now());
        session.setIsCompleted(true);
        return programSessionRepository.save(session);
    }
} 