package com.example.ceom.service.mysql.impl;

import com.example.ceom.dto.IEmployeeDTO;
import com.example.ceom.dto.IEmployeeFindByIdDTO;
import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.entity.sqlserver.Personal;
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
    public List<IEmployeeDTO> findAllDTO() {
        return employeeRepository.findAllEmployeeDTO();
    }

    @Override
    public IEmployeeFindByIdDTO findEmployeeDTO(int id) {
        return employeeRepository.findEmployeeDTOById(id);
    }
}
