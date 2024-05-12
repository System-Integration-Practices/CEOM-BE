package com.example.ceom.service.mysql.impl;

import com.example.ceom.entity.mysql.PayRate;
import com.example.ceom.exception.NotFoundException;
import com.example.ceom.repository.mysql.PayRateRepository;
import com.example.ceom.service.mysql.PayRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayRateServiceImpl implements PayRateService {
    @Autowired
    private PayRateRepository payRateRepository;

    @Override
    public List<PayRate> findAll() {
        return payRateRepository.findAll();
    }

    @Override
    public PayRate findById(int id) {
        return payRateRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found payrate id"));
    }
}
