package com.example.ceom.controller;

import com.example.ceom.model.request.CreateEmployeeIntegration;
import com.example.ceom.service.sqlserver.EmployeeIntegrationService;
import com.example.ceom.service.sqlserver.impl.EmployeeIntegrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/integration")
@CrossOrigin(origins = "*")
public class EmployeeIntegrationController {
    @Autowired
    private EmployeeIntegrationService employeeIntegrationService;


    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody CreateEmployeeIntegration request){
        employeeIntegrationService.saveEmployeeIntegration(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/update/employee/{employeeNumber}/personal/{personalId}/employment/{employmentId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?>update(@PathVariable int employeeNumber,
                                   @PathVariable int personalId,
                                   @PathVariable int employmentId,
                                   @RequestBody CreateEmployeeIntegration request){
        employeeIntegrationService.updateEmployeeIntegration(request,employeeNumber,personalId,employmentId);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/delete/personal/{personalId}/employment/{employmentId}")
    public ResponseEntity<String> delete(
            @PathVariable int personalId,
            @PathVariable int employmentId) {

        employeeIntegrationService.deleteEmployeeIntegration(personalId, employmentId);

        return ResponseEntity.ok("Employee and related data deleted successfully.");
    }
}
