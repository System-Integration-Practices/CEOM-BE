package com.example.ceom.service.sqlserver.impl;

import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.entity.mysql.PayRate;
import com.example.ceom.entity.sqlserver.Personal;
import com.example.ceom.model.request.CreateIntegration;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.repository.mysql.PayRateRepository;
import com.example.ceom.repository.sqlserver.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IntegrationServiceImpl {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PayRateRepository payRateRepository;

    @Transactional
    public void saveIntegration(CreateIntegration request){
        Personal personal = new Personal();
        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .ssn(request.getSsn())
                .payRate(request.getPayRate())
                .employeeNumber(2)
                .build();
        personal.setFirstName(request.getFirstName());
        employeeRepository.save(employee);
//        personRepository.save(personal);


    }
}
