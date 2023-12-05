package com.example.BankApplicationUseCase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankExceptionHandler {

    @ExceptionHandler(value = {javax.security.auth.login.AccountNotFoundException.class})
    public ResponseEntity<Object> handlAccountNotFoundException
            (AccountNotFoundException accountNoFoundException)
    {
        BankException bankExceptionn= new BankException(
                accountNoFoundException.getMessage(),
                accountNoFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(bankExceptionn,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InsufficentBalance.class})
    public ResponseEntity<Object> handlInsufficentBalance
            (InsufficentBalance insufficentBalance)
    {
        BankException bankExceptionn= new BankException(
                insufficentBalance.getMessage(),
                insufficentBalance.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(bankExceptionn,HttpStatus.NOT_FOUND);
    }

}
