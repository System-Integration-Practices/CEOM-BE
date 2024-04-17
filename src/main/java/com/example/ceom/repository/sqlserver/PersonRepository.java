package com.example.ceom.repository.sqlserver;

import com.example.ceom.dto.IPersonalDTO;
import com.example.ceom.dto.PersonalDTO;
import com.example.ceom.model.sqlserver.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Personal, Integer> {
    @Query(value = "SELECT p.PERSONAL_ID, p.CURRENT_FIRST_NAME, p.CURRENT_MIDDLE_NAME, p.CURRENT_LAST_NAME, p.CURRENT_PHONE_NUMBER, p.CURRENT_PERSONAL_EMAIL, p.CURRENT_GENDER, b.PLAN_NAME, e.TOTAL_NUMBER_VACATION_WORKING_DAYS_PER_MONTH\n" +
            "FROM PERSONAL p \n" +
            "JOIN BENEFIT_PLANS b \n" +
            "ON p.BENEFIT_PLAN_ID = b.BENEFIT_PLANS_ID\n" +
            "JOIN EMPLOYMENT\n" +
            "ON p.PERSONAL_ID = EMPLOYMENT.PERSONAL_ID\n" +
            "JOIN EMPLOYMENT_WORKING_TIME e \n" +
            "ON e.EMPLOYMENT_ID = EMPLOYMENT.EMPLOYMENT_ID;\t", nativeQuery = true)
//    @Query(value = "SELECT * FROM PERSONAL ", nativeQuery = true)
    Page<IPersonalDTO> getAllPersonalWithPagination(String find, Pageable pageable);

    @Query(value = "SELECT p.PERSONAL_ID, p.CURRENT_FIRST_NAME, p.CURRENT_MIDDLE_NAME, p.CURRENT_LAST_NAME, p.CURRENT_PHONE_NUMBER, p.CURRENT_PERSONAL_EMAIL, p.CURRENT_GENDER, b.PLAN_NAME, e.TOTAL_NUMBER_VACATION_WORKING_DAYS_PER_MONTH\n" +
            "FROM PERSONAL p \n" +
            "JOIN BENEFIT_PLANS b \n" +
            "ON p.BENEFIT_PLAN_ID = b.BENEFIT_PLANS_ID\n" +
            "JOIN EMPLOYMENT\n" +
            "ON p.PERSONAL_ID = EMPLOYMENT.PERSONAL_ID\n" +
            "JOIN EMPLOYMENT_WORKING_TIME e \n" +
            "ON e.EMPLOYMENT_ID = EMPLOYMENT.EMPLOYMENT_ID;\t", nativeQuery = true)
    List<IPersonalDTO> getAllPersonal();
}
