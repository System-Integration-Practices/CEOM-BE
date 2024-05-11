package com.example.ceom.service.mysql;

import com.example.ceom.model.mysql.PayRate;

import java.util.List;

public interface PayRateService {
    List<PayRate> findAll();

    PayRate findById(int id);
}
