package com.example.ceom.repository.sqlserver;

import com.example.ceom.entity.sqlserver.EmploymentWorkingTime;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmploymentWorkingTimeRepository extends CrudRepository<EmploymentWorkingTime, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE EMPLOYMENT_WORKING_TIME WHERE EMPLOYMENT_ID = :id", nativeQuery = true)
    void deleteByEmploymentId(@Param("id") int id);
}
