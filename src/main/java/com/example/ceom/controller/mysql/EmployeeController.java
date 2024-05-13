package com.example.ceom.controller.mysql;

import com.example.ceom.dto.IEmployeeDTO;
import com.example.ceom.dto.IEmployeeFindByIdDTO;
import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.service.mysql.EmployeeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mysql/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

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

    @GetMapping("/getEmployeeDTOById/{id}")
    public ResponseEntity<IEmployeeFindByIdDTO> findEmployeeDTOById(@PathVariable int id){
        IEmployeeFindByIdDTO employeeDTO = employeeService.findEmployeeDTO(id);
        if (employeeDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }
}
