package com.example.ceom.service.sqlserver;

import com.example.ceom.dto.IEmploymentDTO;
import com.example.ceom.entity.sqlserver.Employment;

import java.util.List;

public interface EmploymentService {
    List<Employment> findAll();

    IEmploymentDTO findEmploymentDTOById(int id);
}
