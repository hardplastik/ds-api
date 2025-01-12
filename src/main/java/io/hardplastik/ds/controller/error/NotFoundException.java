package io.hardplastik.ds.controller.error;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessLogicException {

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
    
}