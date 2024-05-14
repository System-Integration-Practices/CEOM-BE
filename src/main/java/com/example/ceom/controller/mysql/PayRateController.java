package com.example.ceom.controller.mysql;

import com.example.ceom.entity.mysql.PayRate;
import com.example.ceom.service.mysql.PayRateService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mysql/payrate")
@CrossOrigin(origins = "*")
public class PayRateController {
    @Autowired
    PayRateService payRateService;

    @GetMapping("/list")
    ResponseEntity<List<PayRate>> findAllPayRate() {
        List<PayRate> payRates = payRateService.findAll();
        return ResponseEntity.ok(payRates);
    }
}
