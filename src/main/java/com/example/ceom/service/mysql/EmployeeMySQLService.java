package com.example.ceom.service.mysql;

import com.example.ceom.dto.IEmployeeDTO;
import com.example.ceom.model.mysql.Employee;

import java.util.List;

public interface EmployeeMySQLService {
    List<IEmployeeDTO> findAll();
}
