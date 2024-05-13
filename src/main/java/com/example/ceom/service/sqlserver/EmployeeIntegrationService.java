package com.example.ceom.service.sqlserver;

import com.example.ceom.model.request.CreateEmployeeIntegration;

public interface EmployeeIntegrationService {
    void saveEmployeeIntegration(CreateEmployeeIntegration request);
    void deleteEmployeeIntegration(int personalId, int employmentId);
    void updateEmployeeIntegration(CreateEmployeeIntegration request, int employeeNumber, int personalId, int employmentId);
}