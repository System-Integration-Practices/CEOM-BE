package com.example.ceom.dto;

public interface IEmployeeFindByIdDTO {
    int getIdEmployee();
    int getEmployeeNumber();
    String getFirstName();
    String getLastName();
    double getPaidLastYear();
    double getPaidToDate();
    String getPayRate();
    double getSsn();
    int getVacationDays();
    int getIdPayRates();
}
