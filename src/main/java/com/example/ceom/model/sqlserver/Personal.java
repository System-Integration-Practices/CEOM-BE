package com.example.ceom.model.sqlserver;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "PERSONAL")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSONAL_ID")
    private int personalId;

    @Column(name = "CURRENT_FIRST_NAME")
    private String firstName;

    @Column(name = "CURRENT_LAST_NAME")
    private String lastName;

    @Column(name = "CURRENT_MIDDLE_NAME")
    private String middleInitial;

    @Column(name = "BIRTH_DATE")
    private Date birthday;

    @Column(name = "SOCIAL_SECURITY_NUMBER")
    private String ssn;

    @Column(name = "DRIVERS_LICENSE")
    private String driversLicense;

    @Column(name = "CURRENT_ADDRESS_1")
    private String address1;

    @Column(name = "CURRENT_ADDRESS_2")
    private String address2;

    @Column(name = "CURRENT_CITY")
    private String city;

    @Column(name = "CURRENT_COUNTRY")
    private String country;

    @Column(name = "CURRENT_ZIP")
    private int zip;

    @Column(name = "CURRENT_GENDER")
    private boolean gender;

    @Column(name = "CURRENT_PERSONAL_EMAIL")
    private String email;

    @Column(name = "CURRENT_PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "CURRENT_MARITAL_STATUS")
    private String maritalStatus;

    @Column(name = "ETHNICITY")
    private String ethnicity;

    @Column(name = "SHAREHOLDER_STATUS")
    private boolean shareholderStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personal", orphanRemoval = true)
    private List<Employment> employmentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BENEFIT_PLAN_ID",  referencedColumnName = "BENEFIT_PLANS_ID")
    private BenefitPlans benefitPlans;

    @Override
    public String toString() {
        return "Person{" +
                "employeeId=" + personalId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + country + '\'' +
                ", zip=" + zip +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", ssn='" + ssn + '\'' +
                ", driversLicense='" + driversLicense + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", gender=" + gender +
                ", shareholderStatus=" + shareholderStatus +
                ", ethnicity='" + ethnicity + '\'' +
                '}';
    }
}
