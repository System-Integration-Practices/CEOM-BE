package com.example.ceom.repository.mysql;

import com.example.ceom.dto.IEmployeeDTO;
import com.example.ceom.entity.mysql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT e.`Employee Number`, e.`idEmployee`, p.`Value`\n" +
            "FROM `employee` as e \n" +
            "JOIN `pay rates` as p ON\n" +
            "e.`Pay Rates_idPay Rates` = p.`idPay Rates`;", nativeQuery = true)
    List<IEmployeeDTO> findAllEmployeeDTO();
}
