package com.example.ceom.service.mysql;

import com.example.ceom.entity.mysql.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee request);
    List<Employee>employeeList();

    @Transactional
    void deleteEmployee(Integer employeeNumber);
}
