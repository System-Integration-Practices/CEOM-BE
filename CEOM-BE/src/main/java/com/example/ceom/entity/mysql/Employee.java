package com.example.ceom.entity.mysql;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "employee")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee Number")
    private Integer employeeNumber;

    @Column(name = "First Name")
    private String firstName;

    @Column(name = "Last Name")
    private String lastName;

    @Column(name = "idEmployee")
    private Integer idEmployee;

    @Column(name = "Paid Last Year")
    private String paidLastYear;

    @Column(name = "Paid To Date")
    private String paidToDate;

    @Column(name = "Pay Rate")
    private String payRate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "Pay Rates_idPay Rates", referencedColumnName = "idPay Rates")
    private PayRate payRates;

    @Column(name = "SSN")
    private Double ssn;

    @Column(name = "Vacation Days")
    private Integer vacationDays;


}