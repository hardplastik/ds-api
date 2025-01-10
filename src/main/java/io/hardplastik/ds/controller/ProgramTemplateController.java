package io.hardplastik.ds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.controller.command.ProgramTemplateCommand;
import io.hardplastik.ds.data.ProgramTemplateRepository;
import io.hardplastik.ds.model.ProgramTemplate;

@RestController
@RequestMapping("/programs/templates")
public class ProgramTemplateController {
    
    @Autowired
    private ProgramTemplateRepository repository;

    @GetMapping("")
    public List<ProgramTemplate> list() {
        return repository.findAll();
    }    

    @PostMapping("")
    public ProgramTemplate addProgramTemplate(@RequestBody ProgramTemplateCommand command) {
        ProgramTemplate template = command.toEntity();
        return repository.save(template);
    }

}
