package com.example.ceom.model.sqlserver;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Benefit_Plans")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BenefitPlans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Benefit_Plan_ID")
    private int benefitPlanId;

    @Column(name = "Plan_Name")
    private String planName;

    @Column(name = "Deductable")
    private int deductable;

    @Column(name = "Percentage_CoPay")
    private int percentageCoPay;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benefitPlans", orphanRemoval = true)
    private List<Person> personList = new ArrayList<>();
}
