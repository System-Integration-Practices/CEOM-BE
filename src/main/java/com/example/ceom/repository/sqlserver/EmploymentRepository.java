package com.example.ceom.repository.sqlserver;

import com.example.ceom.entity.sqlserver.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmploymentRepository extends JpaRepository<Employment, Integer> {
    @Query(value = "SELECT MAX(e.employmentId) FROM Employment e")
    int findMaxEmploymentId();
}
