package com.wissen.BillingService.configs;

import com.wissen.BillingService.implementations.BillingTask;
import com.wissen.BillingService.implementations.PenaltyTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PenaltyTask penaltyGenerationTask(){
        return new PenaltyTask();
    }
    @Bean
    public BillingTask billGenerationTask(){
        return new BillingTask();
    }
}
