package com.example.ceom.controller.sqlserver;

import com.example.ceom.entity.sqlserver.BenefitPlans;
import com.example.ceom.model.reponse.MessageResponse;
import com.example.ceom.model.request.CreateBenefitPlans;
import com.example.ceom.service.sqlserver.BenefitPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/benefit")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BenefitPlansController {
    @Autowired
    BenefitPlansService benefitPlansService;

    @PostMapping("/create")
    public ResponseEntity<BenefitPlans> createBenefitPlans(@RequestBody CreateBenefitPlans request) {
        BenefitPlans benefitPlans = benefitPlansService.saveBenefitPlans(request);
        return ResponseEntity.ok(benefitPlans);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteBenefitPlans(@PathVariable int id){
        benefitPlansService.deleteBenefitPlans(id);
        return ResponseEntity.ok(new MessageResponse("Delete benefitPlans is success"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BenefitPlans>updateBenefitPlans(@PathVariable int id, @RequestBody CreateBenefitPlans request){
        BenefitPlans benefitPlans=benefitPlansService.updateBenefitPlans(id,request);
        return ResponseEntity.ok(benefitPlans);
    }
}
