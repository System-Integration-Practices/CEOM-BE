package com.example.ceom.service.integration.impl;

import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.repository.sqlserver.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalIntegrationServiceImpl {
    private final EmployeeRepository employeeMySqlRepository;
    private final PersonRepository personSqlServerRepository;


}
