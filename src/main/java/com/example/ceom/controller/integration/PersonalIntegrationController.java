package com.example.ceom.controller.integration;


import com.example.ceom.dto.PersonalDto;
import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.mysql.PayRate;
import com.example.ceom.model.sqlserver.Personal;
import com.example.ceom.service.mysql.EmployeeService;
import com.example.ceom.service.mysql.PayRateService;
import com.example.ceom.service.sqlservice.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal_integration")
@RequiredArgsConstructor
public class PersonalIntegrationController {
    private final EmployeeService employeeMySqlService;
    private final PersonService personSqlServerService;
    private final PayRateService payRateService;

    @GetMapping
    @Transactional
    public void asyncData(){
        List<Personal> personalList = personSqlServerService.findAll();
        personalList.stream().forEach((item)->{
            Employee employee = employeeMySqlService.findById(item.getPersonalId());
            employee.setFirstName(item.getFirstName());
            employee.setLastName(item.getMiddleInitial() + " " + item.getLastName());
            item.setSsn(""+employee.getSsn());
            employeeMySqlService.save(employee);
            personSqlServerService.save(item);
            System.out.println("Done personal with id = " + item.getPersonalId());
        });

        employeeMySqlService.removeDataUnnecessary(personalList.size());
    }

    private void asyncPersonalEmployee(PersonalDto personalDto, Personal personal, Employee employee){
//        employee = Employee.builder()
//                .firstName(personalDto.getFirstName())
//                .lastName(personalDto.getMiddleInitial() + " " + personalDto.getLastName())
//                .ssn(!(personalDto.getSsn() == null || personalDto.getSsn().isEmpty())?Double.parseDouble(personalDto.getSsn()):0)
//                .build();
        employee.setFirstName(personalDto.getFirstName());
        employee.setLastName(personalDto.getMiddleInitial() + " " + personalDto.getLastName());
        employee.setSsn(!(personalDto.getSsn() == null || personalDto.getSsn().isEmpty())?Double.parseDouble(personalDto.getSsn()):0);

//        personal = Personal.builder()
//                .firstName(personalDto.getFirstName())
//                .lastName(personalDto.getLastName())
//                .middleInitial(personalDto.getMiddleInitial())
//                .ssn(personalDto.getSsn())
//                .email(personalDto.getEmail())
//                .city(personalDto.getCity())
//                .address1(personalDto.getAddress1())
//                .address2(personalDto.getAddress2())
//                .country(personalDto.getCountry())
//                .birthday(personalDto.getBirthday())
//                .zip(personalDto.getZip())
//                .driversLicense(personalDto.getDriversLicense())
//                .phoneNumber(personalDto.getPhoneNumber())
//                .ethnicity(personalDto.getEthnicity())
//                .maritalStatus(personalDto.getMaritalStatus())
//                .shareholderStatus(personalDto.isShareholderStatus())
//                .gender(personalDto.isGender())
//                .build();
        personal.setFirstName(personalDto.getFirstName());
        personal.setLastName(personalDto.getLastName());
        personal.setMiddleInitial(personalDto.getMiddleInitial());
        personal.setSsn(personalDto.getSsn());
        personal.setEmail(personalDto.getEmail());
        personal.setCity(personalDto.getCity());
        personal.setAddress1(personalDto.getAddress1());
        personal.setAddress2(personalDto.getAddress2());
        personal.setCountry(personalDto.getCountry());
        personal.setBirthday(personalDto.getBirthday());
        personal.setZip(personalDto.getZip());
        personal.setDriversLicense(personalDto.getDriversLicense());
        personal.setPhoneNumber(personalDto.getPhoneNumber());
        personal.setEthnicity(personalDto.getEthnicity());
        personal.setMaritalStatus(personalDto.getMaritalStatus());
        personal.setShareholderStatus(personalDto.isShareholderStatus());
        personal.setGender(personalDto.isGender());

    }

    @PostMapping
    public ResponseEntity<?> creaetThePersonal(@RequestBody PersonalDto personalDto){
        System.out.println(personalDto);
        Employee employee = new Employee();
        Personal personal = new Personal();
        asyncPersonalEmployee(personalDto, personal, employee);
        PayRate payRate = payRateService.findById(1);
        employee.setPayRates(payRate);
        personSqlServerService.save(personal);
        employeeMySqlService.save(employee);
        return null;
    }


}
