package com.example.ceom.model.request;

import com.example.ceom.entity.sqlserver.BenefitPlans;
import com.example.ceom.entity.sqlserver.Employment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonalRequest {
    private int personalId;

    private String firstName;

    private String lastName;

    private String middleInitial;

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

//    private List<EmploymentService> employmentList;

    private BenefitPlans benefitPlans;
}
