package com.example.ceom.controller.sqlserver;

import com.example.ceom.response.BenefitPlansResponse;
import com.example.ceom.response.PayRateResponse;
import com.example.ceom.service.mysql.PayRateService;
import com.example.ceom.service.sqlservice.BenefitPlansService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/benefit_plans")
@RequiredArgsConstructor
public class BenefitPlansController {
    private final BenefitPlansService benefitPlansService;
    @GetMapping("")
    public ResponseEntity<List<BenefitPlansResponse>> getAll(){
        return ResponseEntity.ok(benefitPlansService.findAll().stream().map(item -> BenefitPlansResponse.builder()
                .benefitPlanId(item.getBenefitPlanId())
                .planName(item.getPlanName())
                .deductable(item.getDeductable())
                .percentageCoPay(item.getPercentageCoPay())
                .build()
        ).collect(Collectors.toList()));
    }

}
