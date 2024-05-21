package com.example.ceom.response;

import com.example.ceom.model.sqlserver.Personal;
import lombok.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PersonalBirthdayResponse {
    private int personalId;

    private String firstName;

    private String lastName;

    private String middleInitial;

    private String birthday;

    private long countDayToBirthday;

    private String planName;

    private static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static PersonalBirthdayResponse fromPersonal(Personal personal) {
        Date now = new Date();
        int currentYear = Year.now().getValue();
        Date newStart = null;
        Date newEnd = null;
        if(now.getMonth() >= personal.getBirthday().getMonth() && now.getDate() > personal.getBirthday().getDate()){
            currentYear++;
        }
        newStart = new Date();
        newEnd = new Date(currentYear - 1900  , personal.getBirthday().getMonth(), personal.getBirthday().getDate());

        LocalDate start = convertToLocalDateViaInstant(newStart);
        LocalDate end = convertToLocalDateViaInstant(newEnd);
        long diffInDays = -1;
        if(now.getMonth() >= personal.getBirthday().getMonth() && now.getDate() > personal.getBirthday().getDate())diffInDays = ChronoUnit.DAYS.between(end, start);
        else diffInDays = ChronoUnit.DAYS.between(start, end );
        PersonalBirthdayResponse personalResponse = PersonalBirthdayResponse.builder()
                .personalId(personal.getPersonalId())
                .firstName(personal.getFirstName())
                .lastName(personal.getLastName())
                .middleInitial(personal.getMiddleInitial())
                .birthday(personal.getBirthday().toString())
                .countDayToBirthday(Math.abs(diffInDays))
                .planName(personal.getBenefitPlans().getPlanName())
                .build();
        return personalResponse;
    }
}
