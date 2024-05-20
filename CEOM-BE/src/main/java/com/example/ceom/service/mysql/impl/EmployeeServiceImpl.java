package com.example.ceom.service.mysql.impl;

import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.service.mysql.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee request) {
       employeeRepository.save(request);
       return request;
    }

    @Override
    public List<Employee> employeeList() {
        return employeeRepository.findAll();
    }
    @Transactional
    @Override
    public void deleteEmployee(Integer employeeNumber) {
        logger.debug("Attempting to delete employee with employeeNumber: {}", employeeNumber);
        employeeRepository.deleteByEmployeeNumber(employeeNumber);
        logger.debug("Deleted employee with employeeNumber: {}", employeeNumber);
    }
}

