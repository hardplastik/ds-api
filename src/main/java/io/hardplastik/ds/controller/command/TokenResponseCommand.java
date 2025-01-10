package io.hardplastik.ds.controller.command;

import io.hardplastik.ds.model.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseCommand {
    
    private String token;

    private Account account;

}
