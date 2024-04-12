package com.wissen.BillingService.customExceptions;

public class NoUnpaidBillsException extends RuntimeException{
    public NoUnpaidBillsException(){
        super("No bills with pay_status UNPAID");
    }

    public NoUnpaidBillsException(String message){
        super(message);
    }
}
