package com.example.ceom.model.sqlserver;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "JOB_HISTORY")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_HISTORY_ID")
    private int id;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "DIVISION")
    private String division;

    @Column(name = "FROM_DATE")
    private Date startDate;

    @Column(name = "THRU_DATE")
    private Date endDate;

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @Column(name = "SUPERVISOR")
    private String supervisor;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "TYPE_OF_WORK")
    private short typeOfWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYMENT_ID",  referencedColumnName = "EMPLOYMENT_ID")
    private Employment employment;



    @Override
    public String toString() {
        return "JobHistory{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", division='" + division + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", jobTitle='" + jobTitle + '\'' +
                ", supervisor=" + supervisor +
                ", location='" + location + '\'' +
                '}';
    }
}
