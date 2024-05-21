package com.example.ceom.service.sqlservice.impl;

import com.example.ceom.model.sqlserver.Personal;
import com.example.ceom.repository.sqlserver.PersonRepository;
import com.example.ceom.service.sqlservice.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<Personal> getPersonalsByFullNameOrBenefitPlan(String fullName, Integer benefitPlanId, PageRequest pageRequest) {
        return personRepository.searchPersonals(benefitPlanId, fullName, pageRequest);
    }

    @Override
    public Personal findById(int id) {
        return personRepository.findById(id).get();
    }

    @Override
    public Page<Personal> getPersonalsByFullNameOrMonth(String fullName, int month, PageRequest pageRequest) {
        return personRepository.searchPersonalsByMonth(fullName, month, pageRequest);
    }
}
