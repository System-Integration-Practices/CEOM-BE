package com.example.ceom.response;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PayRateResponse {
    private int idPayRate;
    private String payRateName;
    private int taxPercentage;
    private int payType;
    private int payAmount;
    private int ptLevelC;
}
