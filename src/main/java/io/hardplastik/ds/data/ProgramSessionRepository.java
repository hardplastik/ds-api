package io.hardplastik.ds.data;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.hardplastik.ds.model.ProgramSession;
import io.hardplastik.ds.model.projections.ProgramSessionProjection;

@Repository
public interface ProgramSessionRepository extends JpaRepository<ProgramSession, UUID> {
    
  List<ProgramSession> findByUserProgramId(UUID userProgramId);

  Optional<ProgramSessionProjection> findTopByUserProgramIdAndIsCompletedOrderByWeekNumberAscWeekDayAsc(UUID userProgramId, Boolean isCompleted);

  @Query("SELECT ps FROM ProgramSession ps WHERE ps.userProgram.user.id = :userId AND ps.isCompleted = true ORDER BY ps.endDatetime DESC")
  List<ProgramSessionProjection> findLastCompletedSessionsByUserId(@Param("userId") UUID userId, Pageable pageable);

}
