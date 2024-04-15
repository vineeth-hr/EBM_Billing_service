package com.wissen.BillingService.implementations;

import com.wissen.BillingService.exteranalService.MonthlyUsage;
import com.wissen.BillingService.models.Billing;
import com.wissen.BillingService.models.PayStatus;
import com.wissen.BillingService.repositories.BillingRepository;
import com.wissen.BillingService.service.BillingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository repository;

    @Autowired
    private MonthlyUsage monthlyUsage;

    private static final Logger logger = LogManager.getLogger(BillingServiceImpl.class);

    @Override
    public void addBill() {
        List<Long> meters = monthlyUsage.getAllMeterIds();
        if(meters.size() == 0){
            logger.info("Bill Generation Job no meters found "+new Date());
            return;
        }
        for(Long meter: meters){
            logger.info("Bill Generation Job for Meter ID "+meter+ " "+new Date());
//            double totalUsage = monthlyUsage.getMonthlyUsage(meter, LocalDate.now().withDayOfMonth(1).minusDays(1).toString());
            double totalUsage = monthlyUsage.getMonthlyUsage(meter, LocalDate.now().withDayOfMonth(30).toString());

            Billing bill = Billing.builder()
                    .meterId(meter)
                    .unitsInMonth(totalUsage)
                    .amount(totalUsage*8.1)
                    .generatedDate(LocalDate.now())
                    .dueDate(LocalDate.now().plusDays(5))
                    .paymentStatus(PayStatus.UNPAID)
                    .build();
            repository.save(bill);
        }
    }

    @Override
    public List<Billing> getBillsByMeterId(long meterId) {
        return repository.findByMeterId(meterId);
    }

    @Override
    public List<Billing> getBillsByMeterIdAndPaymentStatus(long meterId, PayStatus status){
        return repository.findByPaymentStatus(PayStatus.PAID).stream().filter((bill) -> bill.getMeterId() == meterId).collect(Collectors.toUnmodifiableList());
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
    public Billing getBillsByMonth(long meterId, int month) {
        return getBillByMonthAndYear(meterId, month, LocalDate.now().getYear());
    }

    @Override
    public Billing getBillByMonthAndYear(long meterId, int month, int year) {
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

    @Override
    public Billing getBillByBillId(int billId) {
        return repository.findById(billId).orElse(null);
    }


}
