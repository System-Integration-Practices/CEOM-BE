package com.example.ceom.controller.integration;


import com.example.ceom.dto.PersonalDto;
import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.mysql.PayRate;
import com.example.ceom.model.sqlserver.Personal;
import com.example.ceom.response.PersonalListResponse;
import com.example.ceom.response.PersonalResponse;
import com.example.ceom.service.mysql.EmployeeService;
import com.example.ceom.service.mysql.PayRateService;
import com.example.ceom.service.sqlservice.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("${api.prefix}/personal_integration")
@RequiredArgsConstructor
public class PersonalIntegrationController {
    private final EmployeeService employeeMySqlService;
    private final PersonService personSqlServerService;
    private final PayRateService payRateService;

    @GetMapping("/async")
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
    public ResponseEntity<?> createThePersonalIntegration(@RequestBody PersonalDto personalDto){
        System.out.println(personalDto);
        Employee employee = Employee.builder()
                .firstName(personalDto.getFirstName())
                .lastName(personalDto.getMiddleInitial() + " " + personalDto.getLastName())
                .ssn(!(personalDto.getSsn() == null || personalDto.getSsn().isEmpty())?Double.parseDouble(personalDto.getSsn()):0)
                .build();
        Personal personal = Personal.builder()
                .firstName(personalDto.getFirstName())
                .lastName(personalDto.getLastName())
                .middleInitial(personalDto.getMiddleInitial())
                .ssn(personalDto.getSsn())
                .email(personalDto.getEmail())
                .city(personalDto.getCity())
                .address1(personalDto.getAddress1())
                .address2(personalDto.getAddress2())
                .country(personalDto.getCountry())
                .birthday(personalDto.getBirthday())
                .zip(personalDto.getZip())
                .driversLicense(personalDto.getDriversLicense())
                .phoneNumber(personalDto.getPhoneNumber())
                .ethnicity(personalDto.getEthnicity())
                .maritalStatus(personalDto.getMaritalStatus())
                .shareholderStatus(personalDto.isShareholderStatus())
                .gender(personalDto.isGender())
                .build();
        PayRate payRate = payRateService.findById(1);
        employee.setPayRates(payRate);
        Personal savedPersonal = personSqlServerService.save(personal);
        System.out.println(savedPersonal);
        employee.setEmployeeNumber(savedPersonal.getPersonalId());
        employee.setIdEmployee(savedPersonal.getPersonalId());
        employeeMySqlService.save(employee);
        return ResponseEntity.ok("Create successfully");
    }

    @GetMapping
    public ResponseEntity<PersonalListResponse> getAllPersonal(
            @RequestParam(name = "fullName", defaultValue = "") String fullName,
            @RequestParam(name = "benefitPlanId", required = false)Integer benefitPlanId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ){
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                //Sort.by("createdAt").descending()
                Sort.by("CURRENT_FIRST_NAME").ascending()
        );
        Page<PersonalResponse> list = personSqlServerService.getAllProducts(fullName, benefitPlanId, pageRequest).map(PersonalResponse::fromPersonal);
        List<PersonalResponse> result = list.getContent();
        Set<Integer> ids = result.stream().map(PersonalResponse::getPersonalId).collect(Collectors.toSet());
        List<Employee> employees = employeeMySqlService.findByMultipleIds(ids);
        if(result.size() == employees.size()){
            result = result.stream().flatMap(
                    personal -> employees.stream()
                            .filter(employee-> personal.getPersonalId() == employee.getEmployeeNumber())
                            .map(employee -> {
                                personal.setPayAmount(employee.getPayRates().getPayAmount());
                                return personal;
                            })
            ).toList();
        }else{
            System.out.println("Conflict");
        }
        return ResponseEntity.ok(PersonalListResponse.builder()
                        .personals(list.getContent())
                        .totalPages(list.getTotalPages())
                .build()
        );
    }


}
