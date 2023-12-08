package com.example.BankApplicationUseCase.exception;

public class InsufficentBalance extends RuntimeException {

    public InsufficentBalance(String message) {
        super(message);
    }

    public InsufficentBalance(Throwable cause) {
        super(cause);
    }

    public InsufficentBalance() {
        super("Insufficent Balance");
    }
}
