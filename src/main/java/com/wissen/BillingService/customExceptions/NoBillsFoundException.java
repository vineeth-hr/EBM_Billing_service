package com.wissen.BillingService.customExceptions;

public class NoBillsFoundException extends  RuntimeException{
    public NoBillsFoundException() {
        super("No Bills found");
    }

    public NoBillsFoundException(String message) {
        super(message);
    }
}
