package com.example.ceom.repository.mysql;

import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.mysql.PayRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PayRateRepository extends JpaRepository<PayRate, Integer> {
    @Query(value = "SELECT * FROM `Pay Rates` as p LEFT JOIN  `Employee` as e ON p.`idPay Rates` = e.`Pay Rates_idPay Rates`", nativeQuery = true)
    List<PayRate> fetchAll();
}
