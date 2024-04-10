package com.wissen.BillingService.implementations;

import com.wissen.BillingService.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BillingTask {
    @Autowired
    private BillingService billingService;
    @Scheduled(fixedRateString = "PT5M")
    public void billGenerationTask(){
        billingService.addBill();
    }
}
