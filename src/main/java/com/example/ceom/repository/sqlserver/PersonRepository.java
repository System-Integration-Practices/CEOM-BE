package com.example.ceom.repository.sqlserver;

import com.example.ceom.dto.IPersonalDTO;
import com.example.ceom.entity.sqlserver.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Personal, Integer> {
    @Query(value = "SELECT p.PERSONAL_ID,\n" +
            "       p.CURRENT_FIRST_NAME,\n" +
            "       p.CURRENT_MIDDLE_NAME, \n" +
            "       p.CURRENT_LAST_NAME, \n" +
            "       p.CURRENT_PHONE_NUMBER, \n" +
            "       p.CURRENT_PERSONAL_EMAIL, \n" +
            "       p.CURRENT_GENDER,\t\n" +
            "       b.PLAN_NAME, \n" +
            "       e.TOTAL_NUMBER_VACATION_WORKING_DAYS_PER_MONTH,\n" +
            "\t   em.EMPLOYMENT_ID\n" +
            "       FROM PERSONAL p \n" +
            "       JOIN BENEFIT_PLANS b \n" +
            "       ON p.BENEFIT_PLAN_ID = b.BENEFIT_PLANS_ID\n" +
            "       JOIN EMPLOYMENT em\n" +
            "       ON p.PERSONAL_ID = em.PERSONAL_ID\n" +
            "       JOIN EMPLOYMENT_WORKING_TIME e\n" +
            "       ON e.EMPLOYMENT_ID = em.EMPLOYMENT_ID;", nativeQuery = true)
    Page<IPersonalDTO> getAllPersonal(String find, Pageable pageable);
}
