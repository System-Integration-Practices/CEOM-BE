package com.example.ceom.controller.sqlserver;

import com.example.ceom.dto.EmployeeDTO;
import com.example.ceom.dto.IEmployeeDTO;
import com.example.ceom.dto.IPersonalDTO;
import com.example.ceom.dto.PersonalDTO;
import com.example.ceom.model.sqlserver.Personal;
import com.example.ceom.response.MapAndTotalPageResponse;
import com.example.ceom.service.mysql.EmployeeMySQLService;
import com.example.ceom.service.sqlserver.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
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
    public ResponseEntity<MapAndTotalPageResponse> getAllListPersonal(@RequestParam(defaultValue = "") String find, @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<IPersonalDTO> personalPage = personalService.findAll(find, pageable);
        if (personalPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<IPersonalDTO> personals = personalPage.getContent();
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

        MapAndTotalPageResponse response = new MapAndTotalPageResponse(employeeDTOMap, personalPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public List<Personal> getAllPersonal() {
        return this.personalService.getAll();
    }
}
