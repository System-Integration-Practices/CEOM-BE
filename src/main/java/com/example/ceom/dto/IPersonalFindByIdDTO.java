package com.example.ceom.dto;

import java.util.Date;

public interface IPersonalFindByIdDTO {
    int getPERSONAL_ID();

    String getCURRENT_FIRST_NAME();

    String getCURRENT_LAST_NAME();

    String getCURRENT_MIDDLE_NAME();

    Date getBIRTH_DATE();

    String getSOCIAL_SECURITY_NUMBER();

    String getDRIVER_LICENSE();

    String getCURRENT_ADDRESS_1();

    String getCURRENT_ADDRESS_2();

    String getCURRENT_CITY();

    String getCURRENT_COUNTRY();

    int getCURRENT_ZIP();

    String getCURRENT_MARITAL_STATUS();

    String getETHNICITY();

    int getSHAREHOLDER_STATUS();

    String getCURRENT_PHONE_NUMBER();

    String getCURRENT_PERSONAL_EMAIL();

    String getCURRENT_GENDER();

    int getBENEFIT_PLAN_ID();
}
