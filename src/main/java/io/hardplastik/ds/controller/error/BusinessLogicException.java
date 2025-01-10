package io.hardplastik.ds.controller.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessLogicException extends RuntimeException {
    
    private String description;

    private HttpStatus status;

    public BusinessLogicException(String description, HttpStatus status) {
        super(description);
        this.description = description;
        this.status = status;
    }

}