package com.example.ceom.model.request;

import com.example.ceom.dto.EmployeeDTO;
import com.example.ceom.dto.PersonalDTO;
import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.entity.sqlserver.Employment;
import com.example.ceom.entity.sqlserver.Personal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeIntegration {
//    private String firstName;
//    private String lastName;
//    private double ssn;
    private PersonalDTO personal;
    private EmployeeDTO employee;
    private Employment employment;
}
