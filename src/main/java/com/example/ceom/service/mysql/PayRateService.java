package com.example.ceom.service.mysql;

import com.example.ceom.entity.mysql.PayRate;

import java.util.List;

public interface PayRateService {
    List<PayRate> findAll();

    PayRate findById(int id);
}
