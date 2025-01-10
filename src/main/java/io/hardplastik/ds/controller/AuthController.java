package io.hardplastik.ds.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hardplastik.ds.controller.command.LoginCommand;
import io.hardplastik.ds.controller.command.TokenResponseCommand;
import io.hardplastik.ds.controller.command.UserCommand;
import io.hardplastik.ds.controller.error.BusinessLogicException;
import io.hardplastik.ds.controller.error.CredentialsInvalidException;
import io.hardplastik.ds.data.AccountRepository;
import io.hardplastik.ds.model.Account;
import io.hardplastik.ds.service.JwtService;
import io.hardplastik.ds.util.PasswordUtil;


@RestController
@RequestMapping("/auth")
@Transactional(readOnly = true)
public class AuthController {
    
    @Autowired
    private AccountRepository repository;

    @Autowired
    private JwtService jwtService;
    
    @Transactional
    @PostMapping("/sign-up")
    public Account signUp(@RequestBody UserCommand command) {
        
        Account user = command.toEntity();

        Optional<Account> optUser = repository
            .findByUsername(command.getUsername());

        if (optUser.isPresent()) {
            throw new BusinessLogicException("username already in use", HttpStatus.BAD_REQUEST);
        }

        String passwordSalt = PasswordUtil.generateSalt();
        String passwordHash = PasswordUtil.hashPassword(command.getPassword(), passwordSalt);

        user.setPasswordSalt(passwordSalt);
        user.setPasswordHash(passwordHash);
        user.setCreatedAt(LocalDateTime.now());

        return repository.save(user);

    }

    @PostMapping("/login")
    public TokenResponseCommand login(@RequestBody LoginCommand command) {

        Account account = repository
            .findByUsername(command.getUsername())
            .orElseThrow(() -> new CredentialsInvalidException("username or password may be invalid"));
        
        Boolean isPasswordValid = PasswordUtil.verifyPassword(command.getPassword(), account.getPasswordSalt(), account.getPasswordHash());

        if (!isPasswordValid) {
            throw new CredentialsInvalidException("username or password may be invalid");
        }

        TokenResponseCommand response = new TokenResponseCommand();
        response.setToken(jwtService.buildToken(account));
        account.setPasswordHash(null);
        account.setPasswordSalt(null);
        response.setAccount(account);
        return response;
    }
    

}
