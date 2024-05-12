package com.example.ceom.controller.sqlserver;

import com.example.ceom.entity.sqlserver.Personal;
import com.example.ceom.model.reponse.MessageResponse;
import com.example.ceom.model.request.CreatePersonalRequest;
import com.example.ceom.service.sqlserver.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sqlserver/personal")
@CrossOrigin(origins = "*")
public class PersonalController {
    @Autowired
    private PersonalService personalService;

    @PostMapping("/create")
    public ResponseEntity<Personal> createPersonal(@RequestBody CreatePersonalRequest request){
        Personal personal = personalService.savePersonal(request);
        return ResponseEntity.ok(personal);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deletePersonal(@PathVariable int id){
        personalService.deletePersonal(id);
        return ResponseEntity.ok(new MessageResponse("Delete Personal is success!!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Personal> updatePersonal(@PathVariable int id, @RequestBody CreatePersonalRequest request){
        Personal personal = personalService.updatePersonal(id, request);
        return ResponseEntity.ok(personal);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Personal>> getAllPersonal() {
        List<Personal> personals = personalService.findAll();
        return ResponseEntity.ok(personals);
    }
}
