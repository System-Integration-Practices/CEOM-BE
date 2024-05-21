package com.example.ceom.repository.mysql;

import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.sqlserver.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e JOIN FETCH e.payRates")
    List<Employee> fetchAll();

    @Query("SELECT e FROM Employee e JOIN FETCH e.idEmployee")
    Employee fetchById(int id);


    @Query("SELECT e FROM Employee e JOIN FETCH e.payRates WHERE e.employeeNumber IN ?1")
    List<Employee> findByMultiplyIds(Set<Integer> ids);


    @Modifying
    @Query("DELETE FROM Employee e WHERE e.employeeNumber > ?1")
    void removeDataUnnecessary(int conditionNumber);

    @Query(value = "SELECT * FROM Employee e LEFT JOIN `Pay Rates` pr ON e.`Employee Number`  = pr.`idPay Rates` WHERE  (:fullName IS NULL OR concat_ws(' ',e.`First Name` ,e.`Last Name`) LIKE CONCAT('%', :fullName, '%'))",
            nativeQuery = true
    )
    Page<Employee> searchEmployees(
            @Param("fullName") String fullName,
            Pageable pageable
    );
}
