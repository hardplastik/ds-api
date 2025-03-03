package io.hardplastik.ds.model.projections;

import java.time.LocalDateTime;
import java.util.UUID;

import io.hardplastik.ds.model.enums.Goal;
import io.hardplastik.ds.model.enums.Intensity;
import io.hardplastik.ds.model.enums.Methodology;
import io.hardplastik.ds.model.enums.Phase;

public interface UserProgramProjection {
  
  UUID getId();

  String getName();

  LocalDateTime getEnrollDatetime();
  
  Boolean getIsStarted();

  Boolean getIsCompleted();

  Integer getWeeks();

  Integer getSessionsPerWeek();
  
  UserProjection getUser();

  Phase getPhase();

  Goal getGoal();

  Methodology getMethodology();

  Intensity getIntensity();

  Integer getHighSeries();

  Integer getLowSeries();

  Integer getMinReps();

  Integer getMaxReps();

  Integer getRirMin();

  Integer getRirMax();

}
