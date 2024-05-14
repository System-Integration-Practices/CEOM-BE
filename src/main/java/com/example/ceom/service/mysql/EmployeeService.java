package com.example.ceom.service.mysql;

import com.example.ceom.dto.IEmployeeDTO;
import com.example.ceom.dto.IEmployeeFindByIdDTO;
import com.example.ceom.entity.mysql.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee request);

    List<Employee> employeeList();

    List<IEmployeeDTO> findAllDTO();

    IEmployeeFindByIdDTO findEmployeeDTO(int id);
}
