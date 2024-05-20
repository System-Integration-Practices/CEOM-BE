package com.example.ceom.repository.sqlserver;

import com.example.ceom.dto.IEmploymentDTO;
import com.example.ceom.entity.sqlserver.Employment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<Employment, Integer> {

    @Query(value = "SELECT e.EMPLOYMENT_ID, e.EMPLOYMENT_CODE, e.EMPLOYMENT_STATUS, e.WORKERS_COMP_CODE, e.HIRE_DATE_FOR_WORKING, e.TERMINATION_DATE, e.REHIRE_DATE_FOR_WORKING, e.LAST_REVIEW_DATE, e.NUMBER_DAYS_REQUIREMENT_OF_WORKING_PER_MONTH, e.PERSONAL_ID\n" +
            "FROM EMPLOYMENT e\n" +
            "WHERE e.EMPLOYMENT_ID = :id", nativeQuery = true)
    IEmploymentDTO findEmploymentDTOById(@Param("id") int id);
    @Modifying
    @Transactional
    @Query(value = "DELETE EMPLOYMENT\n" +
            "WHERE PERSONAL_ID = :id", nativeQuery = true)
    void deleteEmploymentByPersonalId(@Param("id") int id);

    @Query(value = "SELECT * FROM EMPLOYMENT\n" +
            "WHERE PERSONAL_ID = :id", nativeQuery = true)
    List<Employment> findAllEmploymentByPersonalId(@Param("id") int id);
}
