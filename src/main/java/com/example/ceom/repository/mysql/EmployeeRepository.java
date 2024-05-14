package com.example.ceom.repository.mysql;

import com.example.ceom.model.mysql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e JOIN FETCH e.payRates")
    List<Employee> fetchAll();

    @Query("SELECT e FROM Employee e JOIN FETCH e.idEmployee")
    Employee fetchById(int id);


    @Query("SELECT e FROM Employee e JOIN FETCH e.payRates WHERE e.employeeNumber IN ?1")
    List<Employee> findByMultiplyIds(List<Integer> ids);


    @Modifying
    @Query("DELETE FROM Employee e WHERE e.employeeNumber > ?1")
    void removeDataUnnecessary(int conditionNumber);
}
