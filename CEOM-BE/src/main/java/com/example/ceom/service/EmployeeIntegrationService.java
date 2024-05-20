package com.example.ceom.service;

import com.example.ceom.model.request.CreateEmployeeIntegration;

public interface EmployeeIntegrationService {
    void saveEmployeeIntegration(CreateEmployeeIntegration request);
    void deleteEmployeeIntegration(Integer employeeNumber, Integer personalId, Integer employmentId);
    void updateEmployeeIntegration(Integer employeeNumber, Integer personalId, Integer employmentId,CreateEmployeeIntegration request);
}