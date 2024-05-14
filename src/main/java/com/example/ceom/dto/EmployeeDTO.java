package com.example.ceom.dto;

import com.example.ceom.entity.mysql.PayRate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private int employeeNumber;

    private String firstName;

    private String lastName;

    private int idEmployee;

    private double paidLastYear;

    private double paidToDate;

    private String payRate;

    private int idPayRate;

    private double ssn;

    private int vacationDays;
}
