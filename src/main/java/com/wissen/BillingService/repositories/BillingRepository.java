package com.wissen.BillingService.repositories;

import com.wissen.BillingService.models.Billing;
import com.wissen.BillingService.models.PayStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import java.util.List;

public interface BillingRepository extends JpaRepository<Billing, Integer> {
    List<Billing> findByMeterId(long meterId);
    List<Billing> findByPaymentStatus(PayStatus status);
//    @Query("SELECT b FROM bill b WHERE b.METER_ID = :meterId AND MONTH(b.GENERATED_DATE) = :month AND YEAR(b.GENERATED_DATE) = :year")
//    Optional<Billing> getBillByMonthAndYear(@Param("meterId") Long meterId, @Param("month") int month, @Param("year") int year);

    @Query("SELECT b FROM Billing b WHERE b.meterId = :meterId AND MONTH(b.generatedDate) = :month AND YEAR(b.generatedDate) = :year")
    Optional<Billing> getBillByMonthAndYear(@Param("meterId") Long meterId, @Param("month") int month, @Param("year") int year);
}
