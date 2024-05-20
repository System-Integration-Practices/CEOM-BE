package com.example.ceom.repository.sqlserver;

import com.example.ceom.dto.IPersonalDTO;
import com.example.ceom.dto.IPersonalFindByIdDTO;
import com.example.ceom.dto.IPersonalSyncDTO;
import com.example.ceom.entity.sqlserver.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Query(value = "SELECT p.PERSONAL_ID,\n" +
            "p.CURRENT_FIRST_NAME,\n" +
            "p.CURRENT_MIDDLE_NAME,\n" +
            "p.CURRENT_LAST_NAME,\n" +
            "p.BIRTH_DATE,\n" +
            "p.SOCIAL_SECURITY_NUMBER,\n" +
            "p.DRIVERS_LICENSE,\n" +
            "p.CURRENT_ADDRESS_1,\n" +
            "p.CURRENT_ADDRESS_2,\n" +
            "p.CURRENT_CITY,\n" +
            "p.CURRENT_COUNTRY,\n" +
            "p.CURRENT_ZIP,\n" +
            "p.CURRENT_GENDER,\n" +
            "p.CURRENT_PERSONAL_EMAIL,\n" +
            "p.CURRENT_PHONE_NUMBER,\n" +
            "p.CURRENT_MARITAL_STATUS,\n" +
            "p.ETHNICITY,\n" +
            "p.SHAREHOLDER_STATUS,\n" +
            "p.BENEFIT_PLAN_ID\n" +
            "FROM PERSONAL p WHERE p.PERSONAL_ID = :id", nativeQuery = true)
    IPersonalFindByIdDTO findPersonalDTOById(@Param("id") int id);

    @Query(value = "SELECT p.CURRENT_FIRST_NAME, p.CURRENT_MIDDLE_NAME, p.CURRENT_LAST_NAME, e.EMPLOYMENT_ID\n" +
            "FROM PERSONAL p \n" +
            "JOIN EMPLOYMENT e\n" +
            "ON e.PERSONAL_ID = p.PERSONAL_ID", nativeQuery = true)
    List<IPersonalSyncDTO> findAllPersonalSyncDTO();
}
