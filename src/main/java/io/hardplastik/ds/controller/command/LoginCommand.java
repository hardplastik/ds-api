package io.hardplastik.ds.controller.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCommand {
    
    private String username;

    private String password;

}
