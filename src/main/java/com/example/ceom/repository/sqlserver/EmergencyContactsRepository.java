package com.example.ceom.repository.sqlserver;


import com.example.ceom.entity.sqlserver.EmploymentWorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactsRepository extends JpaRepository<EmploymentWorkingTime, Integer> {
}
