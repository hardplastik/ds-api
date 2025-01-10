package io.hardplastik.ds.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.controller.command.UserProgramCommand;
import io.hardplastik.ds.data.UserProgramRepository;
import io.hardplastik.ds.model.UserProgram;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user-program")
public class UserProgramController {

    @Autowired
    private UserProgramRepository userProgramRepository;

    @GetMapping("")
    public List<UserProgram> getAllUserPrograms() {
        return userProgramRepository.findAll();
    }

    @GetMapping("/{id}")
    public UserProgram getUserProgramById(@PathVariable UUID id) {
        return userProgramRepository.findById(id).orElse(null);
    }

    @Transactional
    @PostMapping("")
    public UserProgram createUserProgram(@RequestBody UserProgramCommand command) {
        UserProgram userProgram = command.toEntity();
        return userProgramRepository.save(userProgram);
    }
    
}
