package com.example.ceom.repository.sqlserver;

import com.example.ceom.entity.sqlserver.BenefitPlans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitPlansRepository extends JpaRepository<BenefitPlans, Integer> {
}
