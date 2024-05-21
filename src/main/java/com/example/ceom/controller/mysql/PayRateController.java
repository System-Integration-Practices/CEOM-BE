package com.example.ceom.controller.mysql;


import com.example.ceom.model.mysql.PayRate;
import com.example.ceom.response.PayRateResponse;
import com.example.ceom.service.mysql.PayRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/pay_rate")
@RequiredArgsConstructor
public class PayRateController {
    private final PayRateService payRateService;
    @GetMapping("")
    public ResponseEntity<List<PayRateResponse>> getAll(){
        return ResponseEntity.ok(payRateService.findAll().stream().map(item -> PayRateResponse.builder()
                .idPayRate(item.getIdPayRate())
                .payRateName(item.getPayRateName())
                .payType(item.getPayType())
                .payAmount(item.getPayAmount())
                .taxPercentage(item.getTaxPercentage())
                .ptLevelC(item.getPtLevelC())
                .build()
        ).collect(Collectors.toList()));
    }
}
