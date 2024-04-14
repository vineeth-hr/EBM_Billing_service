
package com.wissen.BillingService.customExceptions;

import org.springframework.http.HttpStatus;

public class NoPaidBillsException extends RuntimeException{
    public NoPaidBillsException(){
        super("No bills with pay_status PAID");
    }

    public NoPaidBillsException(String message){
        super(message);
    }
}
