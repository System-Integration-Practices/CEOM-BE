package com.example.ceom.service.mysql.impl;

import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.entity.sqlserver.Personal;
import com.example.ceom.exception.NotFoundException;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.service.mysql.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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

    @Override
    public void deleteEmployee(int employeeNumber) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);
        employeeRepository.delete(employee);
    }


}
