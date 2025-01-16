package io.hardplastik.ds.model.projections;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserProgramProjection {
  
  UUID getId();

  String getName();

  LocalDateTime getEnrollDatetime();
  
  Boolean getIsStarted();

  Boolean getIsCompleted();

  Integer getWeeks();

  Integer getSessionsPerWeek();
  
  UserProjection getUser();
}
