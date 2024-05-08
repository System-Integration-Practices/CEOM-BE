package com.example.ceom.model.mysql;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "employee")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee Number")
    private int employeeNumber;

    @Column(name = "First Name")
    private String firstName;

    @Column(name = "Last Name")
    private String lastName;

    @Column(name = "idEmployee")
    private int idEmployee;

    @Column(name = "Paid Last Year")
    private double paidLastYear;

    @Column(name = "Paid To Date")
    private double paidToDate;

    @Column(name = "Pay Rate")
    private String payRate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Pay Rates_idPay Rates",  referencedColumnName = "idPay Rates")
    private PayRate payRates;

    @Column(name = "SSN")
    private double ssn;
//
    @Column(name = "Vacation Days")
    private int vacationDays;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idEmployee=" + idEmployee +
                ", paidLastYear=" + paidLastYear +
                ", paidToDate=" + paidToDate +
                ", payRate='" + payRate + '\'' +
                ", ssn=" + ssn +
                ", vacationDays=" + vacationDays +
                '}';
    }
}
