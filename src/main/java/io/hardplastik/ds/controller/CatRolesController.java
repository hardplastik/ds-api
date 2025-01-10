package io.hardplastik.ds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.controller.command.CatRolesCommand;
import io.hardplastik.ds.data.CatRolesRepository;
import io.hardplastik.ds.model.catalogs.CatRoles;

@RestController
@RequestMapping("/roles")
public class CatRolesController {
    
    @Autowired
    private CatRolesRepository repository;

    @GetMapping("")
    public List<CatRoles> listRoles() {
        return repository.findAll();
    }

    @PostMapping("")
    public CatRoles addRole(@RequestBody CatRolesCommand command) {
        CatRoles newRole = command.toEntity();
        return repository.save(newRole);
    }
    
}
