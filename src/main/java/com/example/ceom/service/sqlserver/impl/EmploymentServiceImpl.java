package com.example.ceom.service.sqlserver.impl;

import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.service.sqlserver.EmploymentService;
import org.springframework.stereotype.Service;

@Service
public class EmploymentServiceImpl implements EmploymentService {
    private PersonalServiceImpl personalService;
    private EmployeeRepository employeeRepository;

}
