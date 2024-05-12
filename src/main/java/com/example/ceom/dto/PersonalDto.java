package com.example.ceom.dto;

import com.example.ceom.model.mysql.PayRate;
import com.example.ceom.model.sqlserver.BenefitPlans;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalDto {
    @JsonProperty("personalId")
    private int personalId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("middleInitial")
    private String middleInitial;

    @JsonProperty("birthday")
    private Date birthday;

    @JsonProperty("ssn")
    private String ssn;

    @JsonProperty("driversLicense")
    private String driversLicense;

    @JsonProperty("address1")
    private String address1;

    @JsonProperty("address2")
    private String address2;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;

    @JsonProperty("zip")
    private int zip;

    @JsonProperty("gender")
    private boolean gender;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("maritalStatus")
    private String maritalStatus;

    @JsonProperty("ethnicity")
    private String ethnicity;

    @JsonProperty("shareholderStatus")
    private boolean shareholderStatus;

    @JsonProperty("benefitPlansId")
    private int benefitPlansId;

    @JsonProperty("payRatesId")
    private int payRatesId;
}
