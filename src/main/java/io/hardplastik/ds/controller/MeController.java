package io.hardplastik.ds.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.model.Account;

@RestController
@RequestMapping("/me")
public class MeController {
    
    @GetMapping("")
    public Account me(Account currentUser) {
        return currentUser;
    }
}
