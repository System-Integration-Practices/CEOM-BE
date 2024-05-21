package com.example.ceom.controller.integration;


import com.example.ceom.dto.PersonalDto;
import com.example.ceom.exceptions.DataNotFoundException;
import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.mysql.PayRate;
import com.example.ceom.model.sqlserver.BenefitPlans;
import com.example.ceom.model.sqlserver.Personal;
import com.example.ceom.response.PersonalBirthdayResponse;
import com.example.ceom.response.PersonalListResponse;
import com.example.ceom.response.PersonalResponse;
import com.example.ceom.service.mysql.EmployeeService;
import com.example.ceom.service.mysql.PayRateService;
import com.example.ceom.service.sqlservice.BenefitPlansService;
import com.example.ceom.service.sqlservice.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/personal_integration")
@RequiredArgsConstructor
public class PersonalIntegrationController {
    private final EmployeeService employeeMySqlService;
    private final PersonService personSqlServerService;
    private final PayRateService payRateService;
    private final BenefitPlansService benefitPlansService;

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

    @PostMapping("/employee")
    public ResponseEntity<?> createTheEmployeeIntegration(@RequestBody PersonalDto personalDto) throws DataNotFoundException {
        System.out.println(personalDto);
        try{
            PayRate payRate = payRateService.findById(personalDto.getPayRatesId()).orElseThrow(()->new DataNotFoundException("Not found pay rate"));
            BenefitPlans benefitPlans = benefitPlansService.findById(1).orElseThrow(()->new DataNotFoundException("Not found benefit plan"));
            Employee employee = Employee.builder()
                    .firstName(personalDto.getFirstName())
                    .lastName(personalDto.getMiddleInitial() + " " + personalDto.getLastName())
                    .ssn(!(personalDto.getSsn() == null || personalDto.getSsn().isEmpty())?Double.parseDouble(personalDto.getSsn()):0)
                    .payRates(payRate)
                    .paidToDate(personalDto.getPaidToDate())
                    .paidLastYear(personalDto.getPaidLastYear())
                    .vacationDays(personalDto.getVacationDays())
                    .payRate(payRate.getPayRateName())
                    .build();

            Personal personal = Personal.builder()
                    .firstName(personalDto.getFirstName())
                    .lastName(personalDto.getLastName())
                    .middleInitial(personalDto.getMiddleInitial())
                    .ssn(personalDto.getSsn())
                    .birthday(personalDto.getBirthday())
                    .gender(personalDto.isGender())
                    .build();

            Personal savedPersonal = personSqlServerService.save(personal);
            System.out.println(savedPersonal);
            employee.setEmployeeNumber(savedPersonal.getPersonalId());
            employee.setIdEmployee(savedPersonal.getPersonalId());
            employeeMySqlService.save(employee);
            return ResponseEntity.ok("Create successfully");
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> createThePersonalIntegration(@RequestBody PersonalDto personalDto) throws DataNotFoundException {
        System.out.println(personalDto);
        try{
            PayRate payRate = payRateService.findById(personalDto.getPayRatesId()).orElseThrow(()->new DataNotFoundException("Not found pay rate"));
            BenefitPlans benefitPlans = benefitPlansService.findById(personalDto.getBenefitPlansId()).orElseThrow(()->new DataNotFoundException("Not found benefit plan"));

            Employee employee = Employee.builder()
                    .firstName(personalDto.getFirstName())
                    .lastName(personalDto.getMiddleInitial() + " " + personalDto.getLastName())
                    .ssn(!(personalDto.getSsn() == null || personalDto.getSsn().isEmpty())?Double.parseDouble(personalDto.getSsn()):0)
                    .payRates(payRate)
                    .paidToDate(personalDto.getPaidToDate())
                    .paidLastYear(personalDto.getPaidLastYear())
                    .vacationDays(personalDto.getVacationDays())
                    .payRate(payRate.getPayRateName())
                    .build();

            Personal personal = Personal.builder()
                    .firstName(personalDto.getFirstName())
                    .lastName(personalDto.getLastName())
                    .middleInitial(personalDto.getMiddleInitial())
                    .ssn(personalDto.getSsn())
                    .birthday(personalDto.getBirthday())
                    .gender(personalDto.isGender())
                    .benefitPlans(benefitPlans)
                    .build();

        Personal savedPersonal = personSqlServerService.save(personal);
        System.out.println(savedPersonal);
        employee.setEmployeeNumber(savedPersonal.getPersonalId());
        employee.setIdEmployee(savedPersonal.getPersonalId());
        employeeMySqlService.save(employee);
            return ResponseEntity.ok("Create successfully");
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<?> getAllPersonal(
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
        Page<PersonalResponse> list = personSqlServerService.getPersonalsByFullNameOrBenefitPlan(fullName, benefitPlanId, pageRequest).map(PersonalResponse::fromPersonal);
        List<PersonalResponse> result = list.getContent();
        Set<Integer> ids = result.stream().map(PersonalResponse::getPersonalId).collect(Collectors.toSet());
        List<Employee> employees = employeeMySqlService.findByMultipleIds(ids);
        if(result.size() == employees.size()){
            result = result.stream().flatMap(
                    personal -> employees.stream()
                            .filter(employee-> personal.getPersonalId() == employee.getEmployeeNumber())
                            .map(employee -> {
                                personal.setPayAmount(employee.getPayRates().getPayAmount());
                                personal.setPayRateName(employee.getPayRates().getPayRateName());
                                return personal;
                            })
            ).toList();
        }else{
            System.out.println("Conflict");
        }
        return ResponseEntity.ok(PersonalListResponse.builder()
                        .personals(result)
                        .totalPages(list.getTotalPages())
                        .currentPage(list.getNumber())
                        .itemPerPage(list.getNumberOfElements())
                .build()
        );
    }

    @GetMapping("/birthday")
    public ResponseEntity<?> getPersonalsForAlert(
            @RequestParam(name = "fullName", defaultValue = "") String fullName,
            @RequestParam(name = "month", required = false)Integer month,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ){
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                //Sort.by("createdAt").descending()
                Sort.by("CURRENT_FIRST_NAME").ascending()
        );
        if(month == null){
            Calendar cal= Calendar.getInstance();
            month = cal.get(Calendar.MONTH)+1;
        }
        Page<Personal> personalPage = personSqlServerService.getPersonalsByFullNameOrMonth(fullName, month, pageRequest);
        Page<PersonalBirthdayResponse> list = personalPage.map(PersonalBirthdayResponse::fromPersonal);
        List<PersonalBirthdayResponse> result = list.getContent();
        return ResponseEntity.ok(PersonalListResponse.builder()
                .personals(result)
                .totalPages(list.getTotalPages())
                .currentPage(list.getNumber())
                .itemPerPage(list.getNumberOfElements())
                .build()
        );
    }


}
