package com.example.ceom.model.mysql;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "pay rates")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPay Rates")
    private int idPayRate;

    @Column(name = "Pay Rate Name")
    private String payRateName;

    @Column(name = "Value")
    private int value;

    @Column(name = "Tax Percentage")
    private int taxPercentage;

    @Column(name = "Pay Type")
    private int payType;

    @Column(name = "Pay Amount")
    private int payAmount;

    @Column(name = "PT - Level C")
    private int ptLevelC;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payRates", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Employee> employees = new ArrayList<>();

    @Override
    public String toString() {
        return "PayRate{" +
                "idPayRate=" + idPayRate +
                ", payRateName='" + payRateName + '\'' +
                ", value=" + value +
                ", taxPercentage=" + taxPercentage +
                ", payType=" + payType +
                ", payAmount=" + payAmount +
                ", ptLevelC=" + ptLevelC +
                ", employees=" + employees +
                '}';
    }
}
