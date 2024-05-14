package com.example.ceom.dto;

import com.example.ceom.entity.sqlserver.BenefitPlans;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDTO {
    private int personalId;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date birthday;

    private String ssn;

    private String driversLicense;

    private String address1;

    private String address2;

    private String city;

    private String country;

    private int zip;

    private boolean gender;

    private String email;

    private String phoneNumber;

    private String maritalStatus;

    private String ethnicity;

    private boolean shareholderStatus;

    private int benefitPlanId;
}
