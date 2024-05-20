package com.example.ceom.controller;

import com.example.ceom.dto.IPersonalSyncDTO;
import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.model.request.CreateEmployeeIntegration;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.repository.sqlserver.PersonRepository;
import com.example.ceom.service.sqlserver.EmployeeIntegrationService;
import com.example.ceom.service.sqlserver.impl.EmployeeIntegrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/integration")
@CrossOrigin(origins = "*")
public class EmployeeIntegrationController {
    @Autowired
    private EmployeeIntegrationService employeeIntegrationService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateEmployeeIntegration request) {
        employeeIntegrationService.saveEmployeeIntegration(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/update/employee/{employeeNumber}/personal/{personalId}/employment/{employmentId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> update(@PathVariable int employeeNumber,
                                    @PathVariable int personalId,
                                    @PathVariable int employmentId,
                                    @RequestBody CreateEmployeeIntegration request) {
        employeeIntegrationService.updateEmployeeIntegration(request, employeeNumber, personalId, employmentId);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/delete/personal/{personalId}/employment/{employmentId}")
    public ResponseEntity<String> delete(
            @PathVariable int personalId,
            @PathVariable int employmentId) {

        employeeIntegrationService.deleteEmployeeIntegration(personalId, employmentId);

        return ResponseEntity.ok("Employee and related data deleted successfully.");
    }

    @GetMapping("/data-synchronization")
    public ResponseEntity<?> synchronization() {
        List<IPersonalSyncDTO> personalSyncDTOS = personRepository.findAllPersonalSyncDTO();
        List<Employee> employeeList = employeeRepository.findAll();

        for (IPersonalSyncDTO p : personalSyncDTOS) {
            for (Employee e : employeeList) {
                if (e.getIdEmployee() == p.getEMPLOYMENT_ID()) {
                    e.setFirstName(p.getCURRENT_FIRST_NAME());
                    e.setLastName(p.getCURRENT_MIDDLE_NAME() + " " + p.getCURRENT_LAST_NAME());
                    employeeRepository.save(e);
                }
            }
        }
        return ResponseEntity.ok("Data sync successfully!");
    }
}
