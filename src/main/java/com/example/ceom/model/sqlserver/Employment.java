package com.example.ceom.model.sqlserver;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity(name = "Employment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMPLOYMENT_ID")
    private int employmentId;

    @Column(name = "EMPLOYMENT_CODE")
    private String employmentCode;

    @Column(name = "EMPLOYMENT_STATUS")
    private String employmentStatus;

    @Column(name = "HIRE_DATE_FOR_WORKING")
    private Date hireDateForWorking;

    @Column(name = "WORKERS_COMP_CODE")
    private String workersCompCode;

    @Column(name = "TERMINATION_DATE")
    private Date terminationDate;

    @Column(name = "REHIRE_DATE_FOR_WORKING")
    private Date rehireDateForWorking;

    @Column(name = "LAST_REVIEW_DATE")
    private Date lastReviewDate;

    @Column(name = "NUMBER_DAYS_REQUIREMENT_OF_WORKING_PER_MONTH")
    private int daysWorkingPerMonth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSONAL_ID",  referencedColumnName = "PERSONAL_ID")
    @JsonBackReference
    private Personal personal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employment", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<JobHistory> jobHistories = new ArrayList<>();
}
