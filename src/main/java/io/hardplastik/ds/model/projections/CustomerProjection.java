package io.hardplastik.ds.model.projections;

import java.time.LocalDate;

public interface CustomerProjection {
  
  Float getWeight();

  Float getHeight();

  LocalDate getDayOfBirth();

  String getPhoneNumber();
}
