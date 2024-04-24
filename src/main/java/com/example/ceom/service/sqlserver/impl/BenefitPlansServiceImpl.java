package com.example.ceom.service.sqlserver.impl;

import com.example.ceom.entity.sqlserver.BenefitPlans;
import com.example.ceom.exception.NotFoundException;
import com.example.ceom.model.request.CreateBenefitPlans;
import com.example.ceom.repository.sqlserver.BenefitPlansRepository;
import com.example.ceom.service.sqlserver.BenefitPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenefitPlansServiceImpl implements BenefitPlansService {
    @Autowired
    private BenefitPlansRepository benefitPlansRepository;

    @Override
    public BenefitPlans saveBenefitPlans(CreateBenefitPlans request) {
        BenefitPlans benefitPlans= new BenefitPlans();
        benefitPlans.setPlanName(request.getPlanName());
        benefitPlans.setDeductable(request.getDeductable());
        benefitPlans.setPercentageCoPay(request.getPercentageCoPay());
        benefitPlansRepository.save(benefitPlans);
        return benefitPlans;
    }

    @Override
    public void deleteBenefitPlans(int id) {
        BenefitPlans benefitPlans= benefitPlansRepository.findById(id).orElseThrow(()->new NotFoundException("Not found benefit id"));
        benefitPlansRepository.delete(benefitPlans);
    }

    @Override
    public BenefitPlans updateBenefitPlans(int id, CreateBenefitPlans request) {
        BenefitPlans benefitPlans=benefitPlansRepository.findById(id).orElseThrow(()->new NotFoundException("Not found benefit id"));
        benefitPlans.setPlanName(request.getPlanName());
        benefitPlans.setDeductable(request.getDeductable());
        benefitPlans.setPercentageCoPay(request.getPercentageCoPay());
        benefitPlansRepository.save(benefitPlans);
        return benefitPlans;
    }
}
