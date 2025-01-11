package io.hardplastik.ds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.hardplastik.ds.controller.error.CredentialsInvalidException;
import io.hardplastik.ds.data.AccountRepository;
import jakarta.transaction.Transactional;

@Service
public class AccountService implements UserDetailsService {
    
    @Autowired
    private AccountRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
            .orElseThrow(() -> new CredentialsInvalidException("username or password may be invalid"));
    }

}
