package com.example.ceom.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalDTO {
    private int PERSONAL_ID;
    private String CURRENT_FIRST_NAME;
    private String CURRENT_LAST_NAME;
    private String CURRENT_MIDDLE_NAME;
    private String CURRENT_PHONE_NUMBER;
    private String CURRENT_PERSONAL_EMAIL;
    private String CURRENT_GENDER;
    private String PLAN_NAME;
}
