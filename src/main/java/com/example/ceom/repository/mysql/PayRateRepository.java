package com.example.ceom.repository.mysql;

import com.example.ceom.model.mysql.PayRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRateRepository extends JpaRepository<PayRate, Integer> {
}
