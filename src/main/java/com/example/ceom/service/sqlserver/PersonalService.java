package com.example.ceom.service.sqlserver;

import com.example.ceom.dto.IPersonalDTO;
import com.example.ceom.dto.PersonalDTO;
import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.sqlserver.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonalService {
    Page<IPersonalDTO> findAllWithPagination(String find, Pageable pageable);
    Page<IPersonalDTO> findAll(String find, Pageable pageable);

    List<Personal> getAll();
}
