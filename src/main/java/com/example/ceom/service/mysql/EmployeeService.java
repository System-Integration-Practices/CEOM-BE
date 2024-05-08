package com.example.ceom.service.mysql;

import com.example.ceom.entity.mysql.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee request);
    List<Employee>employeeList();
    void deleteEmployee(int employeeNumber);
}
