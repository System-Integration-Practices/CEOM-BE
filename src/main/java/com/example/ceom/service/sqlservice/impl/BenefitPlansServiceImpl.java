package com.example.ceom.service.sqlservice.impl;

import com.example.ceom.model.sqlserver.BenefitPlans;
import com.example.ceom.repository.sqlserver.BenefitPlansRepository;
import com.example.ceom.service.sqlservice.BenefitPlansService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BenefitPlansServiceImpl implements BenefitPlansService {
    private final BenefitPlansRepository benefitPlansRepository;
    @Override
    public List<BenefitPlans> findAll() {
        return benefitPlansRepository.findAll();
    }
}
