package com.example.ceom.model.sqlserver;

import com.example.ceom.model.mysql.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Personal")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee_ID")
    private int employeeId;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "Middle_Initial")
    private String middleInitial;

    @Column(name = "Address1")
    private String address1;

    @Column(name = "Address2")
    private String address2;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "Zip")
    private int zip;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Social_Security_Number")
    private String ssn;

    @Column(name = "Drivers_License")
    private String driversLicense;

    @Column(name = "Marital_Status")
    private String maritalStatus;

    @Column(name = "Gender")
    private boolean gender;

    @Column(name = "Shareholder_Status")
    private boolean shareholderStatus;

//    @Column(name = "Benefit_Plans")
//    private int benefitPlans;

    @Column(name = "Ethnicity")
    private String ethnicity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", orphanRemoval = true)
    private List<JobHistory> jobHistoryList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Benefit_Plans",  referencedColumnName = "Benefit_Plan_ID")
    private BenefitPlans benefitPlans;
}
