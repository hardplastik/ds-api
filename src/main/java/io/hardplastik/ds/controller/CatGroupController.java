package io.hardplastik.ds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.controller.command.CatMuscleGroupCommand;
import io.hardplastik.ds.data.CatMuscleGroupRepository;
import io.hardplastik.ds.model.catalogs.CatMuscleGroup;

@RestController
@RequestMapping("/exercises/groups")
public class CatGroupController {
  
  @Autowired
  private CatMuscleGroupRepository repository;


  @PostMapping
  public CatMuscleGroup addCatMuscleGroup(@RequestBody CatMuscleGroupCommand command) {
    return repository.save(command.toEntity());
  }

  
}
