package com.example.ceom.entity.sqlserver;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    private int supervisor;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "TYPE_OF_WORK")
    private short typeOfWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYMENT_ID",  referencedColumnName = "EMPLOYMENT_ID")
    private Employment employment;

}
