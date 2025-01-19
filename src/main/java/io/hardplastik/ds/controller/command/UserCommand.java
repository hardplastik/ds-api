package io.hardplastik.ds.controller.command;

import io.hardplastik.ds.model.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCommand {
    
    private String username;

    private String name;

    private String lastName;

    private String password;

    public Account toEntity() {
        Account account = new Account();
        account.setUsername(username);
        account.setName(name);
        account.setLastName(lastName);
        account.setDeleted(Boolean.FALSE);
        account.setIsTrainer(Boolean.TRUE);
        return account;
    }

}
