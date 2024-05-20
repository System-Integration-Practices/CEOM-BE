package com.example.ceom.repository.mysql;

import com.example.ceom.entity.mysql.PayRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayRateRepository extends JpaRepository<PayRate, Integer> {
//    @Query(value = "SELECT * FROM `pay rates` as p LEFT JOIN  `employee` as e ON p.`idPay Rates` = e.`Pay Rates_idPay Rates`", nativeQuery = true)
//    List<PayRate> fetchAll();
}
