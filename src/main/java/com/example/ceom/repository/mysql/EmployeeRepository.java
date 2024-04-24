package com.example.ceom.repository.mysql;

import com.example.ceom.entity.mysql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM employee e JOIN FETCH e.payRates")
    List<Employee> fetchAll();

    @Query("SELECT e FROM employee e JOIN FETCH e.idEmployee")
    Employee fetchById(int id);
}
