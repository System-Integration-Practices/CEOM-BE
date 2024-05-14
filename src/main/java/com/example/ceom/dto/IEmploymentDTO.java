package com.example.ceom.dto;

import java.util.Date;

public interface IEmploymentDTO {
     int getEMPLOYMENT_ID();

     String getEMPLOYMENT_CODE();

    String getEMPLOYMENT_STATUS();

    Date getHIRE_DATE_FOR_WORKING();

    String getWORKERS_COMP_CODE();

    Date getTERMINATION_DATE();

    Date getREHIRE_DATE_FOR_WORKING();

    Date getLAST_REVIEW_DATE();

    int getNUMBER_DAYS_REQUIREMENT_OF_WORKING_PER_MONTH();

    int getPERSONAL_ID();
}
