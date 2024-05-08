package com.example.ceom.controller;

import com.example.ceom.model.request.CreateEmployeeIntegration;
import com.example.ceom.service.EmployeeIntegrationService;
import com.example.ceom.service.impl.EmployeeIntegrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/integration")
@CrossOrigin(origins = "*")
public class EmployeeIntegrationController {
    @Autowired
    private EmployeeIntegrationService employeeIntegrationService;


    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody CreateEmployeeIntegration request){
        employeeIntegrationService.saveEmployeeIntegration(request);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/delete/employee/{employeeNumber}/personal/{personalId}/employment/{employmentId}")
    public ResponseEntity<String> delete(
            @PathVariable Integer employeeNumber,
            @PathVariable Integer personalId,
            @PathVariable Integer employmentId) {

        employeeIntegrationService.deleteEmployeeIntegration(employeeNumber, personalId, employmentId);

        return ResponseEntity.ok("Employee and related data deleted successfully.");
    }

    @PutMapping("/update/employee/{employeeNumber}/personal/{personalId}/employment/{employmentId}")
    public ResponseEntity<?>update(@PathVariable Integer employeeNumber,
                                   @PathVariable Integer personalId,
                                   @PathVariable Integer employmentId,
                                   @RequestBody CreateEmployeeIntegration request){
        employeeIntegrationService.updateEmployeeIntegration(request,employeeNumber,personalId,employmentId);
        return ResponseEntity.ok(request);
    }
}
