package io.hardplastik.ds.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.controller.command.CreatedUserProgram;
import io.hardplastik.ds.controller.command.UserProgramCommand;
import io.hardplastik.ds.controller.error.BusinessLogicException;
import io.hardplastik.ds.controller.error.NotFoundException;
import io.hardplastik.ds.data.AccountRepository;
import io.hardplastik.ds.data.ProgramTemplateRepository;
import io.hardplastik.ds.data.UserProgramRepository;
import io.hardplastik.ds.model.Account;
import io.hardplastik.ds.model.ProgramTemplate;
import io.hardplastik.ds.model.UserProgram;
import io.hardplastik.ds.service.UserProgramService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/programs")
public class UserProgramController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserProgramRepository userProgramRepository;

    @Autowired
    private ProgramTemplateRepository programTemplateRepository;

    @Autowired
    private UserProgramService userProgramService;

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

    @Transactional
    @PostMapping("/{templateId}/program")
    public UserProgram addProgramToUserProgram(@PathVariable UUID templateId, @RequestBody CreatedUserProgram command) {

        ProgramTemplate programTemplate = programTemplateRepository.findById(templateId)
            .orElseThrow(() -> new NotFoundException("program template not found"));

        Account account = accountRepository.findById(command.getAccountId())
            .orElseThrow(() -> new BusinessLogicException("account not found", HttpStatus.BAD_REQUEST));

        return userProgramRepository
            .save(userProgramService.createProgramForUser(account.getId(), programTemplate));
    }

}
