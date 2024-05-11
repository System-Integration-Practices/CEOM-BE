package com.example.ceom.service.sqlservice;

import com.example.ceom.model.sqlserver.Personal;

import java.util.List;

public interface PersonService {
    Personal save(Personal personal);
    List<Personal> findAll();
    Personal findById(int id);
}
