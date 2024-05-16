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
    @JsonProperty("personalId")
    private int personalId;

    @JsonProperty("firstName")
    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 200 characters")
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String lastName;

    @JsonProperty("middleInitial")
    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String middleInitial;

    @JsonProperty("birthday")
    private Date birthday;

    @JsonProperty("ssn")
    @NotBlank(message = "SSN is required")
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
