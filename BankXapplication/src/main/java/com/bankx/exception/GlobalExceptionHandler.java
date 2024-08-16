package com.bankx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BankException.class)
    public ResponseEntity<?> handleBankException(BankException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
            new Date(),
            ex.getMessage(),
            ex.getErrorCode(),
            request.getDescription(false)
        );
        
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (ex.getErrorCode().equals("CUSTOMER_NOT_FOUND")) {
            status = HttpStatus.NOT_FOUND;
        }
        
        return new ResponseEntity<>(errorDetails, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
            new Date(),
            ex.getMessage(),
            "INTERNAL_ERROR",
            request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}