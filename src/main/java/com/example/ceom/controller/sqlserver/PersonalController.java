package com.example.ceom.controller.sqlserver;

import com.example.ceom.dto.EmployeeDTO;
import com.example.ceom.dto.IEmployeeDTO;
import com.example.ceom.dto.IPersonalDTO;
import com.example.ceom.dto.ListPersonDTO;
import com.example.ceom.entity.sqlserver.Personal;
import com.example.ceom.model.reponse.MapAndTotalPageResponse;
import com.example.ceom.model.reponse.MessageResponse;
import com.example.ceom.model.request.CreatePersonalRequest;
import com.example.ceom.service.mysql.EmployeeService;
import com.example.ceom.service.sqlserver.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/sqlserver/personal")
@CrossOrigin(origins = "*")
public class PersonalController {
    @Autowired
    private PersonalService personalService;

    @Autowired
    private EmployeeService employeeService;

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

    @GetMapping("/list-pagination")
    public ResponseEntity<MapAndTotalPageResponse> getAllListPersonal(@RequestParam(defaultValue = "") String find, @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<IPersonalDTO> personalPage = personalService.findAll(find, pageable);
        if (personalPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<IPersonalDTO> personals = personalPage.getContent();
        List<IEmployeeDTO> employeeDTOList = employeeService.findAllDTO();
        Map<Integer, ListPersonDTO> listPersonDTOMap = new HashMap<>();

        for (IPersonalDTO person : personals) {
            ListPersonDTO personDTO = new ListPersonDTO();
            personDTO.setPERSONAL_ID(person.getPERSONAL_ID());
            personDTO.setCURRENT_FIRST_NAME(person.getCURRENT_FIRST_NAME());
            personDTO.setCURRENT_MIDDLE_NAME(person.getCURRENT_MIDDLE_NAME());
            personDTO.setCURRENT_LAST_NAME(person.getCURRENT_LAST_NAME());
            personDTO.setCURRENT_PHONE_NUMBER(person.getCURRENT_PHONE_NUMBER());
            personDTO.setCURRENT_PERSONAL_EMAIL(person.getCURRENT_PERSONAL_EMAIL());
            personDTO.setCURRENT_GENDER(person.getCURRENT_GENDER());
            personDTO.setPLAN_NAME(person.getPLAN_NAME());
            personDTO.setTOTAL_NUMBER_VACATION_WORKING_DAYS_PER_MONTH(person.getTOTAL_NUMBER_VACATION_WORKING_DAYS_PER_MONTH());
            personDTO.setEmployeeNumber(person.getEMPLOYMENT_ID());
//            personDTO.setIdEmployee(person.getEMPLOYMENT_ID());
            personDTO.setEMPLOYMENT_ID(person.getEMPLOYMENT_ID());
            listPersonDTOMap.put(person.getEMPLOYMENT_ID(), personDTO);
        }

        for (IEmployeeDTO payAmount : employeeDTOList) {
            ListPersonDTO personDTO = listPersonDTOMap.get(payAmount.getIdEmployee());
            if (personDTO != null) {
                personDTO.setPayAmount(payAmount.getValue());
            }
        }

        MapAndTotalPageResponse response = new MapAndTotalPageResponse(listPersonDTOMap, personalPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
