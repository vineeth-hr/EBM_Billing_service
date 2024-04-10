package com.wissen.BillingService.exteranalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

import java.time.LocalDate;

@FeignClient("METERSERVICE")
public interface MonthlyUsage {
    @GetMapping("/usage/")
    Double getMonthlyUsage(@RequestParam("meter_id") long meter_id, @RequestParam("date")LocalDate date);

    @GetMapping("/meters")
    List<Long> getAllMeterIds();
}
