package io.hardplastik.ds.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.hardplastik.ds.controller.error.BusinessLogicException;
import io.hardplastik.ds.controller.error.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ErrorResponse> handleBusinessLogicException(BusinessLogicException ex) {

        String code = convertClassNameToCode(ex.getClass().getSimpleName());
        ErrorResponse errorResponse = new ErrorResponse(code, ex.getDescription());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }


    
    private String convertClassNameToCode(String className) {
        return className
                .replaceAll("Exception", "")
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
    }

}
