package com.example.ceom.controller.mysql;

import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.model.reponse.MessageResponse;
import com.example.ceom.repository.sqlserver.EmploymentRepository;
import com.example.ceom.service.mysql.EmployeeService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    private EmploymentRepository employmentRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @PostMapping("/create")
    public ResponseEntity<Employee>saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Employee>>listEmployee(){
        List<Employee>employees=employeeService.employeeList();
        return ResponseEntity.ok(employees);
    }
    @DeleteMapping("/delete/{employeeNumber}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer employeeNumber) {
        logger.debug("Received request to delete employee with employeeNumber: {}", employeeNumber);
        employeeService.deleteEmployee(employeeNumber);
        logger.debug("Successfully deleted employee with employeeNumber: {}", employeeNumber);
        return ResponseEntity.noContent().build();
    }
}
