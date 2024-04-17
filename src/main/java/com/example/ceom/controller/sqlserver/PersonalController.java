package com.example.ceom.controller.sqlserver;

import com.example.ceom.dto.EmployeeDTO;
import com.example.ceom.dto.IEmployeeDTO;
import com.example.ceom.dto.IPersonalDTO;
import com.example.ceom.dto.PersonalDTO;
import com.example.ceom.model.sqlserver.Personal;
import com.example.ceom.service.mysql.EmployeeMySQLService;
import com.example.ceom.service.sqlserver.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sqlserver/personal")
public class PersonalController {
    @Autowired
    private PersonalService personalService;
    @Autowired
    private EmployeeMySQLService employeeMySQLService;

    @GetMapping("/list-pagination")
    public ResponseEntity<?> getAllListPersonalPagination(@RequestParam(defaultValue = "") String find,
                                                @RequestParam(value = "page") Integer page) {
        Page<IPersonalDTO> personalList = personalService.findAllWithPagination(find, PageRequest.of(page, 5));
        if (personalList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Page<IPersonalDTO>>(personalList, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllListPersonal() {
        List<IPersonalDTO> personals = personalService.findAll();
        if (personals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<IEmployeeDTO> employeeDTOList = employeeMySQLService.findAll();

        Map<Integer, EmployeeDTO> employeeDTOMap = new HashMap<>();

        for (IPersonalDTO person : personals) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setPERSONAL_ID(person.getPERSONAL_ID());
            employeeDTO.setCURRENT_FIRST_NAME(person.getCURRENT_FIRST_NAME());
            employeeDTO.setCURRENT_MIDDLE_NAME(person.getCURRENT_MIDDLE_NAME());
            employeeDTO.setCURRENT_LAST_NAME(person.getCURRENT_LAST_NAME());
            employeeDTO.setCURRENT_PHONE_NUMBER(person.getCURRENT_PHONE_NUMBER());
            employeeDTO.setCURRENT_PERSONAL_EMAIL(person.getCURRENT_PERSONAL_EMAIL());
            employeeDTO.setCURRENT_GENDER(person.getCURRENT_GENDER());
            employeeDTO.setPLAN_NAME(person.getPLAN_NAME());
            employeeDTO.setTOTAL_NUMBER_VACATION_WORKING_DAYS_PER_MONTH(person.getTOTAL_NUMBER_VACATION_WORKING_DAYS_PER_MONTH());
            employeeDTO.setIdEmployee(person.getPERSONAL_ID());
            employeeDTOMap.put(person.getPERSONAL_ID(), employeeDTO);
        }

        for (IEmployeeDTO payAmount : employeeDTOList) {
            EmployeeDTO employeeDTO = employeeDTOMap.get(payAmount.getIdEmployee());
            if (employeeDTO != null) {
                employeeDTO.setPayAmount(payAmount.getValue());
            }
        }

        return new ResponseEntity<Map<Integer, EmployeeDTO>>(employeeDTOMap, HttpStatus.OK);
    }
}
