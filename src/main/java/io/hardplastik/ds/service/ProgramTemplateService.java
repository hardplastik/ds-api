package io.hardplastik.ds.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.hardplastik.ds.data.ProgramTemplateRepository;
import io.hardplastik.ds.model.ProgramTemplate;

@Service
public class ProgramTemplateService {
    
    @Autowired
    private ProgramTemplateRepository programTemplateRepository;

    public ProgramTemplate findProgramTemplateById(UUID id) {
        return programTemplateRepository.findById(id).orElse(null);
    }

}
