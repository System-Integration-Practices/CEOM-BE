package com.example.ceom.response;

import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.sqlserver.BenefitPlans;
import com.example.ceom.model.sqlserver.Employment;
import com.example.ceom.model.sqlserver.Personal;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PersonalResponse {
    private int personalId;

    private String firstName;

    private String lastName;

    private String middleInitial;

    private boolean gender;

    private int payAmount;

    private String planName;

    private String payRateName;

    public static PersonalResponse fromPersonal(Personal personal) {
        PersonalResponse personalResponse = PersonalResponse.builder()
                .personalId(personal.getPersonalId())
                .firstName(personal.getFirstName())
                .lastName(personal.getLastName())
                .middleInitial(personal.getMiddleInitial())
                .gender(personal.isGender())
                .planName(personal.getBenefitPlans().getPlanName())
                .build();
        return personalResponse;
    }

    public static PersonalResponse merge(PersonalResponse e1, Employee e2) {
        if (e1.getPersonalId() != e2.getEmployeeNumber())
            throw new IllegalArgumentException();
        e1.setPayAmount(e2.getPayRates().getPayAmount());
        return e1;
    }
}
