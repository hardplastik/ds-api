package io.hardplastik.ds.controller.error;

import org.springframework.http.HttpStatus;

public class CredentialsInvalidException extends BusinessLogicException {
    
    public CredentialsInvalidException(String description) {
        super(description, HttpStatus.UNAUTHORIZED);
    }

}
