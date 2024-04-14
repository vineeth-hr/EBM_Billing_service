package com.wissen.BillingService.controllers;

import com.wissen.BillingService.ResponseBodies.BillingResponse;
import com.wissen.BillingService.customExceptions.NoBillsFoundException;
import com.wissen.BillingService.customExceptions.NoPaidBillsException;
import com.wissen.BillingService.customExceptions.NoUnpaidBillsException;
import com.wissen.BillingService.models.Billing;
import com.wissen.BillingService.models.PayStatus;
import com.wissen.BillingService.repositories.BillingRepository;
import com.wissen.BillingService.service.BillingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin("http://localhost:4200")
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    BillingService service;

    @GetMapping("/unpaidByMeterID/{meterID}")
    public List<BillingResponse> getUnpaidBillsByMeterID(@PathVariable long meterID){

        List<Billing> bills =  service.getBillsByMeterIdAndPaymentStatus(meterID, PayStatus.UNPAID);

        if(bills.isEmpty())
            throw new NoUnpaidBillsException();

        List<BillingResponse> responses = new ArrayList<>();

        bills.forEach(bill -> {
              BillingResponse resp = BillingResponse.builder().meterId(bill.getMeterId()).build();
              resp.setBillId(bill.getBillId());
              resp.setUnitsInMonth(bill.getUnitsInMonth());
              resp.setAmount(bill.getAmount());
              resp.setPaymentStatus(bill.getPaymentStatus());
              resp.setPaidDate(bill.getPaidDate());
              resp.setDueDate(bill.getDueDate());
              resp.setGeneratedDate(bill.getGeneratedDate());
              responses.add(resp);
        }
        );

        log.info("Unpaid Bills Meter Id");

        return responses;
    }

    @GetMapping("/paidByMeterID/{meterID}")
    public List<BillingResponse> getPaidBillsByMeterID(@PathVariable long meterID){

        List<Billing> bills = service.getBillsByMeterIdAndPaymentStatus(meterID, PayStatus.PAID);

        if(bills.isEmpty())
            throw new NoPaidBillsException();

        List<BillingResponse> responses = new ArrayList<>();

        bills.forEach(bill -> {
                    BillingResponse resp = BillingResponse.builder().meterId(bill.getMeterId()).build();
                    resp.setBillId(bill.getBillId());
                    resp.setUnitsInMonth(bill.getUnitsInMonth());
                    resp.setAmount(bill.getAmount());
                    resp.setPaymentStatus(bill.getPaymentStatus());
                    resp.setPaidDate(bill.getPaidDate());
                    resp.setDueDate(bill.getDueDate());
                    resp.setGeneratedDate(bill.getGeneratedDate());
                    responses.add(resp);
                }
        );
        log.info("Paid bills meter Id");
        return responses;

    }

    @GetMapping("/allbills")
    public List<BillingResponse> getAllBills(){
        List<Billing> bills = service.getAllBills();List<BillingResponse> responses = new ArrayList<>();

        if(bills.isEmpty())
            throw new NoBillsFoundException();


        bills.forEach(bill -> {


                    BillingResponse resp = BillingResponse.builder().meterId(bill.getMeterId()).build();
                    resp.setBillId(bill.getBillId());
                    resp.setUnitsInMonth(bill.getUnitsInMonth());
                    resp.setAmount(bill.getAmount());
                    resp.setPaymentStatus(bill.getPaymentStatus());
                    resp.setPaidDate(bill.getPaidDate());
                    resp.setDueDate(bill.getDueDate());
                    resp.setGeneratedDate(bill.getGeneratedDate());
                    responses.add(resp);
                }

        );
        log.info("All Bills");
        return responses;


    }

    @GetMapping("/allunpaidbills")
    public List<BillingResponse> getAllUnpaidBills(){
        List<Billing> bills = service.getBillsByPaymentStatus(PayStatus.UNPAID);
        if(bills.isEmpty()) throw new NoUnpaidBillsException();
        List<BillingResponse> responses = new ArrayList<>();

        bills.forEach(bill -> {


                    BillingResponse resp = BillingResponse.builder().meterId(bill.getMeterId()).build();
                    resp.setBillId(bill.getBillId());
                    resp.setUnitsInMonth(bill.getUnitsInMonth());
                    resp.setAmount(bill.getAmount());
                    resp.setPaymentStatus(bill.getPaymentStatus());
                    resp.setPaidDate(bill.getPaidDate());
                    resp.setDueDate(bill.getDueDate());
                    resp.setGeneratedDate(bill.getGeneratedDate());
                    responses.add(resp);
                }

        );
        log.info("All Unpaid Bills");
        return responses;
    }

    @GetMapping("/allpaidbills")
    public List<BillingResponse> getAllPaidBills(){
        List<Billing> bills = service.getBillsByPaymentStatus(PayStatus.PAID);

        if(bills.isEmpty()) throw new NoPaidBillsException();

        List<BillingResponse> responses = new ArrayList<>();

        bills.forEach(bill -> {


                    BillingResponse resp = BillingResponse.builder().meterId(bill.getMeterId()).build();
                    resp.setBillId(bill.getBillId());
                    resp.setUnitsInMonth(bill.getUnitsInMonth());
                    resp.setAmount(bill.getAmount());
                    resp.setPaymentStatus(bill.getPaymentStatus());
                    resp.setPaidDate(bill.getPaidDate());
                    resp.setDueDate(bill.getDueDate());
                    resp.setGeneratedDate(bill.getGeneratedDate());
                    responses.add(resp);
                }

        );
        log.info("All Paid Bills");
        return responses;
    }

    @PutMapping("/update-payment")
    public ResponseEntity<Billing> updatePaymentStatus(int billId) {
        Billing bill = service.updateBillStatusPaid(billId);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

}
