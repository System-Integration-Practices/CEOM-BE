package com.example.ceom.controller;

import com.example.ceom.model.reponse.MessageResponse;
import com.example.ceom.model.request.CreateIntegration;
import com.example.ceom.service.sqlserver.impl.IntegrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("api/integration")
@CrossOrigin(origins = "*")
public class IntegrationController {
    @Autowired
    private IntegrationServiceImpl integrationService;


    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody CreateIntegration request){
        integrationService.saveIntegration(request);

        return ResponseEntity.ok(new MessageResponse("Create Success"));
    }
}
