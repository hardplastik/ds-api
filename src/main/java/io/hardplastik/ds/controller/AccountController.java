package io.hardplastik.ds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.data.AccountRepository;
import io.hardplastik.ds.model.Account;


@RestController
public class AccountController {
  
  @Autowired
  private AccountRepository repository;

  @GetMapping("/clients")
  public List<Account> listClients() {
      return repository.findAll();
  }
  

}
