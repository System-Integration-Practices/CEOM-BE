package com.example.ceom.service.mysql.impl;

import com.example.ceom.model.mysql.Employee;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.service.mysql.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public void removeDataUnnecessary(int conditionNumber) {
        employeeRepository.removeDataUnnecessary(conditionNumber);
    }
}
