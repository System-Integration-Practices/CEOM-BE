package com.example.ceom.model.request;

import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.entity.mysql.PayRate;
import com.example.ceom.entity.sqlserver.Employment;
import com.example.ceom.entity.sqlserver.Personal;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIntegration {
    private String firstName;
    private int idEmployee;
    private String LastName;
    private double ssn;
    private Personal personal;
    private Employee employee;
    private Employment employment;
    private String payRate;
    @JsonProperty("payRateId")
        private int payRateId;
}
