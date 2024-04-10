package com.wissen.BillingService.service;

import com.wissen.BillingService.models.Billing;
import com.wissen.BillingService.models.PayStatus;
import org.apache.el.stream.Optional;

import java.util.List;

public interface BillingService {
    void addBill();
    List<Billing> getBillsByMeterId(long meterId);
    List<Billing> getBillsByPaymentStatus(PayStatus status);
    List<Billing> getAllBills();
    List<Billing> getBillsByMonth(String month);
    Billing getBillByMonthAndYear(Long meterId, int month, int year);
    Billing updateBillStatusPaid(int billId);
    void updateBillStatusUnpaid();
}
