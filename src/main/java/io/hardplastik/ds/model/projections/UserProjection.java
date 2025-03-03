package io.hardplastik.ds.model.projections;

import java.util.UUID;

public interface UserProjection {
  
  UUID getId();

  String getUsername();

  String getName();

  String getLastName();

  String getIsTrainer();

}
