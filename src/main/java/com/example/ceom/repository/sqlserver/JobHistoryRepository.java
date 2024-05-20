package com.example.ceom.repository.sqlserver;

import com.example.ceom.entity.sqlserver.JobHistory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE JOB_HISTORY WHERE EMPLOYMENT_ID = :id", nativeQuery = true)
    void deleteByEmploymentId(@Param("id") int id);
}
