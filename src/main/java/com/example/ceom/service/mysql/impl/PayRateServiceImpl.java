package com.example.ceom.service.mysql.impl;

import com.example.ceom.model.mysql.PayRate;
import com.example.ceom.repository.mysql.PayRateRepository;
import com.example.ceom.service.mysql.PayRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayRateServiceImpl implements PayRateService {
    private final PayRateRepository payRateRepository;

    @Override
    public List<PayRate> findAll() {
        return payRateRepository.findAll();
    }

    @Override
    public PayRate findById(int id) {
        return payRateRepository.findById(id).get();
    }
}
