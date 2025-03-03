package io.hardplastik.ds.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
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
import io.hardplastik.ds.model.projections.UserProgramProjection;
import io.hardplastik.ds.service.UserProgramService;




@RestController
@RequestMapping("")
@Transactional(readOnly = true)
public class UserProgramController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserProgramRepository userProgramRepository;

    @Autowired
    private ProgramTemplateRepository programTemplateRepository;

    @Autowired
    private UserProgramService userProgramService;

    @GetMapping("/programs")
    public List<UserProgram> getAllUserPrograms() {
        return userProgramRepository.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC, "enrollDatetime")));
    }

    @GetMapping("/programs/{id}")
    public UserProgram getUserProgramById(@PathVariable UUID id) {
        return userProgramRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("user program not found"));
    }

    @GetMapping("/clients/{userId}/current-program")
    public UserProgram getMethodName(@PathVariable UUID userId) {
        return userProgramRepository.findTopByUserIdOrderByEnrollDatetimeDesc(userId)
            .orElseThrow(() -> new NotFoundException("No current program found"));
    }
    
    @Transactional
    @PostMapping("/programs")
    public UserProgram createUserProgram(@RequestBody UserProgramCommand command) {
        UserProgram userProgram = command.toEntity();
        return userProgramRepository.save(userProgram);
    }

    @GetMapping("/clients/{userId}/programs")
    public List<UserProgramProjection> listProgramsByUserId(@PathVariable UUID userId) {
        return userProgramRepository.findByUserIdOrderByEnrollDatetimeDesc(userId);
    }
    
    @Transactional
    @PostMapping("/templates/{templateId}/program")
    public UserProgram addProgramToUserProgram(@PathVariable UUID templateId, @RequestBody CreatedUserProgram command) {

        ProgramTemplate programTemplate = programTemplateRepository.findById(templateId)
            .orElseThrow(() -> new NotFoundException("program template not found"));

        Account account = accountRepository.findById(command.getAccountId())
            .orElseThrow(() -> new BusinessLogicException("account not found", HttpStatus.BAD_REQUEST));

        return userProgramRepository
            .save(userProgramService.createProgramForUser(account.getId(), programTemplate));
    }

}
