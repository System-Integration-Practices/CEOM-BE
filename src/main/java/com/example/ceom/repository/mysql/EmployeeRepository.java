package com.example.ceom.repository.mysql;

import com.example.ceom.dto.IEmployeeDTO;
import com.example.ceom.dto.IEmployeeFindByIdDTO;
import com.example.ceom.dto.IEmploymentDTO;
import com.example.ceom.entity.mysql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT e.`Employee Number` as employeeNumber, e.`idEmployee` as idEmployee, p.`Pay Amount` as payAmount\n" +
            "FROM `employee` as e \n" +
            "JOIN `pay rates` as p ON\n" +
            "e.`Pay Rates_idPay Rates` = p.`idPay Rates`;", nativeQuery = true)
    List<IEmployeeDTO> findAllEmployeeDTO();

    @Query(value = "SELECT e.idEmployee as idEmployee, e.`Employee Number` as employeeNumber, e.`First Name` as firstName, e.`Last Name` as lastName,\n" +
            "e.SSN as ssn, e.`Pay Rate` as payRate, e.`Pay Rates_idPay Rates` as idPayRates, e.`Vacation Days` as vacationDays,\n" +
            "e.`Paid To Date` as paidToDate, e.`Paid Last Year` as paidLastYear FROM employee e\n" +
            "WHERE e.`Employee Number` = :id", nativeQuery = true)
    IEmployeeFindByIdDTO findEmployeeDTOById(@Param("id") int id);
}
