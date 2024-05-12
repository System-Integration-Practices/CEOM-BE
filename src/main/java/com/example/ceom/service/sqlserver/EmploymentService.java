package com.example.ceom.service.sqlserver;

import com.example.ceom.entity.sqlserver.Employment;

import java.util.List;

public interface EmploymentService {
    List<Employment> findAll();
}
