package com.example.ceom.service.mysql;

import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.sqlserver.Personal;
import org.springframework.stereotype.Service;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee findById(int id);

    void removeDataUnnecessary(int conditionNumber);
}
