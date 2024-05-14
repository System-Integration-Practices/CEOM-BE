package com.example.ceom.repository.sqlserver;

import com.example.ceom.entity.sqlserver.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Integer> {
}
