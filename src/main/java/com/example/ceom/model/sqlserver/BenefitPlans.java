package com.example.ceom.model.sqlserver;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "BENEFIT_PLANS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BenefitPlans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BENEFIT_PLANS_ID")
    private int benefitPlanId;

    @Column(name = "PLAN_NAME")
    private String planName;

    @Column(name = "DEDUCTABLE")
    private int deductable;

    @Column(name = "PERCENTAGE_COPAY")
    private int percentageCoPay;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benefitPlans", orphanRemoval = true)
    private List<Personal> personList = new ArrayList<>();

    @Override
    public String toString() {
        return "BenefitPlans{" +
                "benefitPlanId=" + benefitPlanId +
                ", planName='" + planName + '\'' +
                ", deductable=" + deductable +
                ", percentageCoPay=" + percentageCoPay +
                '}';
    }
}
