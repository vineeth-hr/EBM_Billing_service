package com.wissen.BillingService.implementations;

import com.wissen.BillingService.models.Billing;
import com.wissen.BillingService.models.PayStatus;
import com.wissen.BillingService.repositories.BillingRepository;
import com.wissen.BillingService.service.BillingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository repository;

    private static final Logger logger = LogManager.getLogger(BillingServiceImpl.class);

    @Override
    public void addBill() {

    }

    @Override
    public List<Billing> getBillsByMeterId(long meterId) {
        return repository.findByMeterId(meterId);
    }

    @Override
    public List<Billing> getBillsByPaymentStatus(PayStatus status) {
        return repository.findByPaymentStatus(status);
    }

    @Override
    public List<Billing> getAllBills() {
        return repository.findAll();
    }

    @Override
    public List<Billing> getBillsByMonth(String month) {
        return null;
    }

    @Override
    public Billing getBillByMonthAndYear(Long meterId, int month, int year) {
        return repository.getBillByMonthAndYear(meterId, month, year).orElse(null);
    }

    @Override
    public Billing updateBillStatusPaid(int billId) {
        Billing bill = repository.findById(billId).orElseThrow();

        LocalDate dueDate = bill.getDueDate();
        if(dueDate.isBefore(LocalDate.now())){
            bill.setPaymentStatus(PayStatus.PAID_PENALTY);
        }
        else{
            bill.setPaymentStatus(PayStatus.PAID);
        }
        bill.setPaidDate(LocalDate.now());
        repository.save(bill);

        return bill;
    }

    @Override
    public void updateBillStatusUnpaid() {
        List<Billing> billsUnpaid = repository.findByPaymentStatus(PayStatus.UNPAID);
        List<Billing> billsUnpaidPenalty = repository.findByPaymentStatus(PayStatus.UNPAID_PENALTY);
        billsUnpaid.addAll(billsUnpaidPenalty);
        if(billsUnpaid.size() == 0){
            logger.info("Penalty Job No bills found "+new Date());
            return;
        }
        for (Billing bill : billsUnpaid) {
            if(bill.getDueDate().isBefore(LocalDate.now())){
                logger.info("Penalty Job bill with id "+bill.getBillId()+" on penalty "+new Date());
                bill.setPaymentStatus(PayStatus.UNPAID_PENALTY);
                bill.setAmount(bill.getAmount()+100);
                repository.save(bill);
            }
        }
    }
}
