package com.example.ceom.service.sqlservice.impl;

import com.example.ceom.model.sqlserver.Personal;
import com.example.ceom.repository.sqlserver.PersonRepository;
import com.example.ceom.service.sqlservice.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public Personal save(Personal personal) {
        return personRepository.save(personal);
    }

    @Override
    public List<Personal> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Personal findById(int id) {
        return personRepository.findById(id).get();
    }
}
