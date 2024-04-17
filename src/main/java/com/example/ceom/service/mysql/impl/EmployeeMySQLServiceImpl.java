package com.example.ceom.service.mysql.impl;

import com.example.ceom.dto.IEmployeeDTO;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.service.mysql.EmployeeMySQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeMySQLServiceImpl implements EmployeeMySQLService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<IEmployeeDTO> findAll() {
        return employeeRepository.findAllEmployeeDTO();
    }
}
