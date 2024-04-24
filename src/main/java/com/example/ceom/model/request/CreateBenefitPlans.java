package com.example.ceom.model.request;

import com.example.ceom.entity.sqlserver.Personal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBenefitPlans {
    private int benefitPlanId;

    private String planName;

    private int deductable;

    private int percentageCoPay;

//    private List<Personal> personalList;
}
