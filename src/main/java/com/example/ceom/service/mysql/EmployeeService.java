package com.example.ceom.service.mysql;

import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.sqlserver.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee findById(int id);

    void removeDataUnnecessary(int conditionNumber);

    List<Employee> findByMultipleIds(Set<Integer> ids);

    Page<Employee> getEmployeesByFullNameOrPayRate(String fullName, PageRequest pageRequest);
}
