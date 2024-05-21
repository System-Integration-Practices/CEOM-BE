package com.example.ceom.dto;

import com.example.ceom.model.mysql.PayRate;
import com.example.ceom.model.sqlserver.BenefitPlans;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalDto {
    @JsonProperty("personal_id")
    private int personalId;

    @JsonProperty("first_name")
    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 200 characters")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String lastName;

    @JsonProperty("middle_initial")
    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String middleInitial;

    @JsonProperty("birthday")
    private Date birthday;

    @JsonProperty("ssn")
    @NotBlank(message = "SSN is required")
    private String ssn;

    @JsonProperty("drivers_license")
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

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("marital_status")
    private String maritalStatus;

    @JsonProperty("ethnicity")
    private String ethnicity;

    @JsonProperty("shareholder_status")
    private boolean shareholderStatus;

    @JsonProperty("benefit_plans_id")
    private int benefitPlansId;

    @JsonProperty("pay_pates_id")
    private int payRatesId;

    @JsonProperty("pay_last_year")
    private double paidLastYear;

    @JsonProperty("paid_to_date")
    private double paidToDate;

    @Column(name = "vacation_days")
    private int vacationDays;
}
