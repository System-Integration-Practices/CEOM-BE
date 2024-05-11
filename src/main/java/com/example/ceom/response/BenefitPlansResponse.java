package com.example.ceom.response;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class BenefitPlansResponse {
    private int benefitPlanId;

    private String planName;

    private int deductable;

    private int percentageCoPay;
}
