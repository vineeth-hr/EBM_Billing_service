package com.wissen.BillingService.ResponseBodies;

import com.wissen.BillingService.models.PayStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillingResponse {
    private long billId;
    private long meterId;
    private double unitsInMonth;
    private double amount;
    private LocalDate generatedDate;
    private LocalDate dueDate;
    private LocalDate paidDate;
    private PayStatus paymentStatus;
}
