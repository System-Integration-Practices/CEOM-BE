package com.example.ceom.controller;

import com.example.ceom.model.request.CreateEmployeeIntegration;
import com.example.ceom.service.sqlserver.impl.EmployeeIntegrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/integration")
@CrossOrigin(origins = "*")
public class EmployeeIntegrationController {
    @Autowired
    private EmployeeIntegrationServiceImpl integrationService;


    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody CreateEmployeeIntegration request){
        integrationService.saveEmployeeIntegration(request);
        return ResponseEntity.ok(request);
    }
}
