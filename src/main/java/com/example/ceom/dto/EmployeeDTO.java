package com.example.ceom.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private int PERSONAL_ID;
    private String CURRENT_FIRST_NAME;
    private String CURRENT_LAST_NAME;
    private String CURRENT_MIDDLE_NAME;
    private String CURRENT_PHONE_NUMBER;
    private String CURRENT_PERSONAL_EMAIL;
    private String CURRENT_GENDER;
    private String PLAN_NAME;
    private int idEmployee;
    private int payAmount;
    private int TOTAL_NUMBER_VACATION_WORKING_DAYS_PER_MONTH;
}
