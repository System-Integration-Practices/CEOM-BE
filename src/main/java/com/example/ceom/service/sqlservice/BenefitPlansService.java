package com.example.ceom.service.sqlservice;

import com.example.ceom.model.sqlserver.BenefitPlans;

import java.util.List;

public interface BenefitPlansService {
    List<BenefitPlans> findAll();
}
