package com.example.ceom.repository.mysql;

import com.example.ceom.entity.mysql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmployeeNumber(Integer employeeNumber);
    @Query(value = "SELECT MAX(e.employeeNumber) FROM employee e")
    int findMaxEmployeeNumber();
    @Modifying
    @Transactional
    @Query("DELETE FROM employee e WHERE e.employeeNumber = :employeeNumber")
    void deleteByEmployeeNumber(@Param("employeeNumber") Integer employeeNumber);
    boolean existsByEmployeeNumber(Integer employeeNumber);
}
