package io.hardplastik.ds.model.projections;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ProgramSessionProjection {

  UUID getId();

  Integer getWeekNumber();

  Integer getWeekDay();

  LocalDateTime getStartDatetime();

  LocalDateTime getEndDatetime();

  Boolean getIsCompleted();
  
  UserProgramProjection getUserProgram();
  
}
