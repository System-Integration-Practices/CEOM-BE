package com.example.ceom.service.sqlserver;

import com.example.ceom.dto.IPersonalDTO;
import com.example.ceom.entity.sqlserver.Personal;
import com.example.ceom.model.request.CreatePersonalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonalService {
    Personal savePersonal(CreatePersonalRequest request);
    void deletePersonal(int personalId);
    Personal updatePersonal(int id, CreatePersonalRequest request);

    List<Personal> findAll();
    Page<IPersonalDTO> findAll(String find, Pageable pageable);
}
