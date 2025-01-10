package io.hardplastik.ds.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.hardplastik.ds.model.Account;

@Service
public class JwtService implements InitializingBean {
    
    @Value("${spring.security.token-secret}")
    private String secret;

    @Value("${spring.security.token-expiration}")
    private Long expiration;

    private JWTVerifier tokenVerifier;

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public void afterPropertiesSet() throws Exception {
        tokenVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
    }

    public String buildToken(Account account) {

        Builder tokenBuilder = JWT.create();
        tokenBuilder.withExpiresAt(getExpirationDate());
        tokenBuilder.withClaim("id", account.getId().toString());
        return tokenBuilder
            .withIssuer("hardplastik")
            .sign(Algorithm.HMAC256(secret));
    }

    public Account deserialize(String token) {

        DecodedJWT decodedToken = tokenVerifier.verify(token);
        byte[] tokenBytes = Base64.getDecoder().decode(decodedToken.getPayload());
        Account account;
        try {
            account = jsonMapper.readValue(tokenBytes, Account.class);
        } catch (IOException e) {
            throw new JWTDecodeException(e.getMessage());
        }
        return account;
        
    }

    private Date getExpirationDate() {
        return Date.from(LocalDateTime.now().plusMinutes(expiration).atZone(ZoneId.systemDefault()).toInstant());
    }

    

}
