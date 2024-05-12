package com.example.ceom.dto;

import com.example.ceom.entity.mysql.PayRate;
import com.example.ceom.entity.sqlserver.Personal;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentDTO {
    private int employmentId;

    private String employmentCode;

    private String employmentStatus;

    private Date hireDateForWorking;

    private String workersCompCode;

    private Date terminationDate;

    private Date rehireDateForWorking;

    private Date lastReviewDate;

    private int daysWorkingPerMonth;

    private int personalId;
}
