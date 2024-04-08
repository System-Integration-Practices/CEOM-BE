package com.example.ceom.model.sqlserver;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "Job_History")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Department")
    private String department;

    @Column(name = "Division")
    private String division;

    @Column(name = "Start_Date")
    private Date startDate;

    @Column(name = "End_Date")
    private Date endDate;

    @Column(name = "Job_Title")
    private String jobTitle;

    @Column(name = "Supervisor")
    private int supervisor;

    @Column(name = "Job_Category")
    private String jobCategory;

    @Column(name = "Location")
    private String location;

    @Column(name = "Departmen_Code")
    private int departmentCode;

    @Column(name = "Salary_Type")
    private int salaryType;

    @Column(name = "Pay_Period")
    private String payPeriod;

    @Column(name = "Hours_per_Week")
    private int hoursPerWeek;

    @Column(name = "Hazardous_Training")
    private boolean hazardousTraining;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Employee_ID",  referencedColumnName = "Employee_ID")
    private Person employeeId;
}
