package com.example.ceom.service.sqlserver.impl;

import com.example.ceom.dto.IEmploymentDTO;
import com.example.ceom.entity.sqlserver.Employment;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.repository.sqlserver.EmploymentRepository;
import com.example.ceom.service.sqlserver.EmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmploymentServiceImpl implements EmploymentService {
    @Autowired
    EmploymentRepository employmentRepository;

    @Override
    public List<Employment> findAll() {
        return employmentRepository.findAll();
    }

    @Override
    public IEmploymentDTO findEmploymentDTOById(int id) {
        return employmentRepository.findEmploymentDTOById(id);
    }
}
