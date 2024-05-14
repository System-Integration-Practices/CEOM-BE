package com.example.ceom.controller.sqlserver;

import com.example.ceom.dto.EmploymentDTO;
import com.example.ceom.dto.IEmploymentDTO;
import com.example.ceom.dto.IPersonalDTO;
import com.example.ceom.entity.sqlserver.Employment;
import com.example.ceom.service.mysql.EmployeeService;
import com.example.ceom.service.sqlserver.EmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sqlserver/employment")
@CrossOrigin(origins = "*")
public class EmploymentController {
    @Autowired
    private EmploymentService employmentService;

    @GetMapping("/getEmploymentDTOById/{id}")
    public ResponseEntity<IEmploymentDTO> findEmploymentDTOById(@PathVariable int id) {
        IEmploymentDTO employmentDTO = employmentService.findEmploymentDTOById(id);
        if (employmentDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employmentDTO, HttpStatus.OK);
    }
}
