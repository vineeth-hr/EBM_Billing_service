package com.wissen.BillingService.controllers;

import com.wissen.BillingService.ResponseBodies.BillingResponse;
import com.wissen.BillingService.models.Billing;
import com.wissen.BillingService.models.PayStatus;
import com.wissen.BillingService.repositories.BillingRepository;
import com.wissen.BillingService.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    BillingService service;

    @GetMapping("/unpaidByMeterID/{meterID}")
    public List<BillingResponse> getUnpaidBillsByMeterID(@PathVariable long meterID){

        List<Billing> bills =  service.getBillsByMeterIdAndPaymentStatus(meterID, PayStatus.UNPAID);
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

        return responses;
    }

    @GetMapping("/paidByMeterID/{meterID}")
    public List<BillingResponse> getPaidBillsByMeterID(@PathVariable long meterID){

        List<Billing> bills = service.getBillsByMeterIdAndPaymentStatus(meterID, PayStatus.PAID);
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

        return responses;

    }

    @GetMapping("/allbills")
    public List<BillingResponse> getAllBills(){
        List<Billing> bills = service.getAllBills();List<BillingResponse> responses = new ArrayList<>();
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

        return responses;


    }

    @GetMapping("/allunpaidbills")
    public List<BillingResponse> getAllUnpaidBills(){
        List<Billing> bills = service.getBillsByPaymentStatus(PayStatus.UNPAID);

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

        return responses;
    }

    @GetMapping("/allpaidbills")
    public List<BillingResponse> getAllPaidBills(){
        List<Billing> bills = service.getBillsByPaymentStatus(PayStatus.PAID);
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

        return responses;
    }

}
