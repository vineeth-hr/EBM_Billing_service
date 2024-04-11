package com.wissen.BillingService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="BILL")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "BILL_ID")
    private Integer billId;

    @Column(name = "METER_ID", nullable = false)
    private Long meterId;

    @Column(name = "UNITS_IN_MONTH", nullable = false)
    private Double unitsInMonth;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Column(name = "GENERATED_DATE", nullable = false)
    private LocalDate generatedDate;

    @Column(name = "DUE_DATE", nullable = false)
    private LocalDate dueDate;

    @Column(name = "PAID_DATE")
    private LocalDate paidDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_STATUS", nullable = false)
    private PayStatus paymentStatus;

}
