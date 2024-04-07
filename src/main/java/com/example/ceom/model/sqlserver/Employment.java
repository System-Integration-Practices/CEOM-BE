package com.example.ceom.model.sqlserver;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity(name = "Employment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employment {
    @Id
    private Long id;

    @Column(name = "Employment_Status")
    private String employeeStatus;

    @Column(name = "Hire_Date")
    private Date hireDate;

    @Column(name = "Workers_Comp_Code")
    private String workersCompCode;

    @Column(name = "Termination_Date")
    private Date terminationDate;

    @Column(name = "Rehire_Date")
    private Date rehireDate;

    @Column(name = "Last_Review_Date")
    private Date lastReviewDate;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Employee_ID")
    private Person person;
}
