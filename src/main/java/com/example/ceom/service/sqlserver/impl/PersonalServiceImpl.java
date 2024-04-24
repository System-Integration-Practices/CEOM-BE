package com.example.ceom.service.sqlserver.impl;

import com.example.ceom.entity.sqlserver.Personal;
import com.example.ceom.exception.NotFoundException;
import com.example.ceom.model.request.CreatePersonalRequest;
import com.example.ceom.repository.sqlserver.PersonRepository;
import com.example.ceom.service.sqlserver.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonalServiceImpl implements PersonalService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Personal savePersonal(CreatePersonalRequest request) {
        Personal personal = new Personal();
        personal.setFirstName(request.getFirstName());
        personal.setLastName(request.getLastName());
        personal.setMiddleInitial(request.getMiddleInitial());
        personal.setBirthday(request.getBirthday());
        personal.setSsn(request.getSsn());
        personal.setDriversLicense(request.getDriversLicense());
        personal.setAddress1(request.getAddress1());
        personal.setAddress2(request.getAddress2());
        personal.setCity(request.getCity());
        personal.setCountry(request.getCountry());
        personal.setZip(request.getZip());
        personal.setGender(request.isGender());
        personal.setEmail(request.getEmail());
        personal.setPhoneNumber(request.getPhoneNumber());
        personal.setMaritalStatus(request.getMaritalStatus());
        personal.setEthnicity(request.getEthnicity());
        personal.setShareholderStatus(request.isShareholderStatus());
//        personal.setEmploymentList(request.getEmploymentList());
        personal.setBenefitPlans(request.getBenefitPlans());
        personRepository.save(personal);
        return personal;
    }

    @Override
    public void deletePersonal(int personalId) {
        Personal personal = personRepository.findById(personalId).orElseThrow(() -> new NotFoundException("Not found personal id" + personalId));
        personRepository.delete(personal);
    }

    @Override
    public Personal updatePersonal(int id, CreatePersonalRequest request) {
        Personal personal =personRepository.findById(id).orElseThrow(()-> new NotFoundException("Not found personal id: "+ id));
        personal.setFirstName(request.getFirstName());
        personal.setLastName(request.getLastName());
        personal.setMiddleInitial(request.getMiddleInitial());
        personal.setBirthday(request.getBirthday());
        personal.setSsn(request.getSsn());
        personal.setDriversLicense(request.getDriversLicense());
        personal.setAddress1(request.getAddress1());
        personal.setAddress2(request.getAddress2());
        personal.setCity(request.getCity());
        personal.setCountry(request.getCountry());
        personal.setZip(request.getZip());
        personal.setGender(request.isGender());
        personal.setEmail(request.getEmail());
        personal.setPhoneNumber(request.getPhoneNumber());
        personal.setMaritalStatus(request.getMaritalStatus());
        personal.setEthnicity(request.getEthnicity());
        personal.setShareholderStatus(request.isShareholderStatus());
        personal.setBenefitPlans(request.getBenefitPlans());
//        personal.setEmploymentList(request.getEmploymentList());
        personRepository.save(personal);
        return personal;
    }
}
