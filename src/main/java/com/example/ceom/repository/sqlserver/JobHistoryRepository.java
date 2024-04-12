package com.example.ceom.repository.sqlserver;

import com.example.ceom.model.sqlserver.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Integer> {
}
