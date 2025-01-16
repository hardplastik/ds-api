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
@RequestMapping("/program-sessions")
public class ProgramSessionController {

    @Autowired
    private ProgramSessionRepository programSessionRepository;

    @GetMapping("")
    public List<ProgramSession> getProgramSessions() {
        return programSessionRepository.findAll();
    }

    @GetMapping("{id}")
    public ProgramSession getProgramSession(@PathVariable UUID id) {
        return programSessionRepository.findById(id).orElse(null);
    }
    
}
