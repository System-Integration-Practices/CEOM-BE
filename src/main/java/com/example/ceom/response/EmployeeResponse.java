package com.example.ceom.response;

import com.example.ceom.model.mysql.Employee;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class EmployeeResponse {
    private int employeeNumber;

    private String firstName;

    private String lastName;

    private double paidLastYear;

    private double paidToDate;

    private String payRate;

    private String payRateName;

    public static EmployeeResponse fromEmployee(Employee employee){
        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .employeeNumber(employee.getEmployeeNumber())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .paidLastYear(employee.getPaidLastYear())
                .paidToDate(employee.getPaidToDate())
                .payRate(employee.getPayRate())
                .payRateName(employee.getPayRates().getPayRateName())
                .build();
        return employeeResponse;
    }
}
