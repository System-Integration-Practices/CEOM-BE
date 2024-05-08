package com.example.ceom.service.sqlserver.impl;

import com.example.ceom.dto.IPersonalDTO;
import com.example.ceom.dto.PersonalDTO;
import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.sqlserver.Personal;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.repository.sqlserver.PersonRepository;
import com.example.ceom.service.sqlserver.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalServiceImpl implements PersonalService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Page<IPersonalDTO> findAllWithPagination(String find, Pageable pageable) {
        return personRepository.getAllPersonalWithPagination(find, pageable);
    }

    @Override
    public Page<IPersonalDTO> findAll(String find, Pageable pageable) {
        return personRepository.getAllPersonal(find, pageable);
    }

    public List<Personal> getAll(){
        return personRepository.findAll();
    }
}
