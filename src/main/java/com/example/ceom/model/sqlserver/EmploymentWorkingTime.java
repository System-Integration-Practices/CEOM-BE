package com.example.ceom.model.sqlserver;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "EMPLOYMENT_WORKING_TIME")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentWorkingTime {
    @Id
    @Column(name = "EMPLOYMENT_WORKING_TIME_ID")
    private Long id;

    @Column(name = "YEAR_WORKING")
    private Date yearWorking;

    @Column(name = "MONTH_WORKING")
    private int monthWorking;

    @Column(name = "NUMBER_DAYS_ACTUAL_OF_WORKING_PER_MONTH")
    private int daysWorkingPerMonth;

    @Column(name = "TOTAL_NUMBER_VACATION_WORKING_DAYS_PER_MONTH")
    private int daysVacation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYMENT_ID",  referencedColumnName = "EMPLOYMENT_ID")
    private Employment employment;
}
