package com.example.ceom.controller.integration;


import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.sqlserver.Personal;
import com.example.ceom.service.mysql.EmployeeService;
import com.example.ceom.service.sqlservice.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/personal_integration")
@RequiredArgsConstructor
public class PersonalIntegrationController {
    private final EmployeeService employeeMySqlService;
    private final PersonService personSqlServerService;

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
}
