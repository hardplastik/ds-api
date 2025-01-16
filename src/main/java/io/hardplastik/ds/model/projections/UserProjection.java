package io.hardplastik.ds.model.projections;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public interface UserProjection {
  
  UUID getId();

  String getUsername();

  String getName();

  String getLastName();

  String getIsTrainer();

  @JsonUnwrapped
  CustomerProjection getCustomer();

}
