package com.example.ceom.service.mysql;

import com.example.ceom.model.mysql.PayRate;

import java.util.List;
import java.util.Optional;

public interface PayRateService {
    List<PayRate> findAll();

    Optional<PayRate> findById(int id);
}
