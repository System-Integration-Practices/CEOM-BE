package com.example.ceom.repository.sqlserver;

import com.example.ceom.entity.sqlserver.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentRepository extends JpaRepository<Employment, Integer> {
}
