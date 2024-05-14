package com.example.ceom.repository.mysql;

import com.example.ceom.entity.mysql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmployeeNumber(Integer employeeNumber);
    @Query(value = "SELECT MAX(e.employeeNumber) FROM employee e")
    int findMaxEmployeeNumber();

}
