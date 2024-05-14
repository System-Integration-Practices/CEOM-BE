package com.example.ceom.service.sqlserver;

import com.example.ceom.entity.sqlserver.BenefitPlans;
import com.example.ceom.model.request.CreateBenefitPlans;

import java.util.List;

public interface BenefitPlansService {
    BenefitPlans saveBenefitPlans(CreateBenefitPlans request);

    void deleteBenefitPlans(int id);

    BenefitPlans updateBenefitPlans(int id, CreateBenefitPlans request);

    List<BenefitPlans> findAll();

    BenefitPlans findById(int id);
}
