package com.example.ceom.service.sqlservice;

import com.example.ceom.model.sqlserver.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PersonService {
    Personal save(Personal personal);
    List<Personal> findAll();

    Page<Personal> getAllProducts(String fullName, Integer benefitPlanId, PageRequest pageRequest);
    Personal findById(int id);
}
