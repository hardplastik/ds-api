package io.hardplastik.ds.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.auth.annotation.CurrentUser;
import io.hardplastik.ds.model.Account;
import io.hardplastik.ds.model.projections.ProgramSessionProjection;
import io.hardplastik.ds.service.ProgramSessionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramSessionController {
  
  private final ProgramSessionService programSessionService;
  
  @GetMapping("/me/current-session")
  public ResponseEntity<ProgramSessionProjection> getCurrentSession(@CurrentUser Account currentUser) {
    return ResponseEntity.of(programSessionService.getCurrentSession(currentUser.getId()));
  }

  @GetMapping("/me/last-sessions")
  public List<ProgramSessionProjection> getLastCompletedSessions(
          @CurrentUser Account currentUser,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "3") int size) {
    return programSessionService.getLastCompletedSessions(
        currentUser.getId(), 
        PageRequest.of(page, Math.min(size, 3))
    );
  }
}
