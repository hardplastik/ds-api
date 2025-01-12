package io.hardplastik.ds.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.controller.command.CustomerDataCommand;
import io.hardplastik.ds.controller.error.NotFoundException;
import io.hardplastik.ds.data.AccountRepository;
import io.hardplastik.ds.model.Account;
import io.hardplastik.ds.model.CustomerData;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/clients")
public class ClientController {
  
  @Autowired
  private AccountRepository repository;
  
  @GetMapping("")
  public List<Account> listClients() {
      return repository.findAll();
  }
  
  @Transactional
  @PutMapping("/{id}")
  public Account updateClientAccount(@PathVariable UUID id, @RequestBody CustomerDataCommand command) {

      Account account = repository.findById(id)
        .orElseThrow(() -> new NotFoundException("client not found"));
        
      CustomerData customer = account.getCustomer() != null 
        ? command.merge(account.getCustomer()) 
        : command.toEntity();

      customer.setAccount(account);
      account.setCustomer(customer);

      return repository.save(account);
  }

  @GetMapping("/{id}")
  public Account getClientAccount(@PathVariable UUID id) {
      return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Account not found"));
  }
  

}
