package io.hardplastik.ds.controller.command;

import io.hardplastik.ds.model.catalogs.CatMuscleGroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatMuscleGroupCommand {
  
  private String name;

  public CatMuscleGroup toEntity() {
    CatMuscleGroup group = new CatMuscleGroup();
    group.setName(name);
    return group;
  }
}
