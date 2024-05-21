package com.example.ceom.service.sqlservice;

import com.example.ceom.model.mysql.PayRate;
import com.example.ceom.model.sqlserver.BenefitPlans;

import java.util.List;
import java.util.Optional;

public interface BenefitPlansService {
    List<BenefitPlans> findAll();

    Optional<BenefitPlans> findById(int id);
}
