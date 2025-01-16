package io.hardplastik.ds.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.data.ProgramSessionRepository;
import io.hardplastik.ds.model.ProgramSession;


@RestController
@RequestMapping("/programs")
public class ProgramSessionController {
  
  @Autowired
  private ProgramSessionRepository repository;

  @GetMapping("/{programId}")
  public List<ProgramSession> getMethodName(@PathVariable UUID programId) {
      return repository.findByUserProgramId(programId);
  }
  

}
