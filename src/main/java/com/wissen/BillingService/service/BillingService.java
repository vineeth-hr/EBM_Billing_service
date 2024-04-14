package com.wissen.BillingService.service;

import com.wissen.BillingService.models.Billing;
import com.wissen.BillingService.models.PayStatus;
import org.apache.el.stream.Optional;

import java.util.List;

public interface BillingService {
    void addBill();
    List<Billing> getBillsByMeterId(long meterId);
    List<Billing> getBillsByPaymentStatus(PayStatus status);
    List<Billing> getBillsByMeterIdAndPaymentStatus(long meterId, PayStatus status);
    List<Billing> getAllBills();
    Billing getBillsByMonth(long meterID, int month);
    Billing getBillByMonthAndYear(long meterId, int month, int year);
    Billing updateBillStatusPaid(int billId);
    void updateBillStatusUnpaid();
}
