package com.example.ceom.repository.sqlserver;

import com.example.ceom.model.sqlserver.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Personal, Integer> {

//    @Query("SELECT p FROM Product p WHERE " +
//            "(:categoryId IS NULL OR :categoryId = 0 OR p.category.id = :categoryId) " +
//            "AND (:keyword IS NULL OR :keyword = '' OR p.name LIKE %:keyword% OR p.description LIKE %:keyword%)")
//    Page<Product> searchProducts
//            (@Param("categoryId") Long categoryId,
//             @Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM PERSONAL e " +
            "LEFT JOIN BENEFIT_PLANS bp " +
            "ON e.BENEFIT_PLAN_ID  = bp.BENEFIT_PLANS_ID " +
            "WHERE  (:fullName IS NULL OR concat_ws(' ',e.CURRENT_FIRST_NAME ,e.CURRENT_MIDDLE_NAME , e.CURRENT_LAST_NAME) LIKE CONCAT('%', :fullName, '%')) " +
            "AND (:benefitPlanId IS NULL OR e.BENEFIT_PLAN_ID = :benefitPlanId)",
            nativeQuery = true
    )
    Page<Personal> searchPersonals(
            @Param("benefitPlanId") Integer benefitPlanId,
             @Param("fullName") String fullName,
             Pageable pageable
    );

    @Query(value = "SELECT * " +
            "FROM PERSONAL e " +
            "LEFT JOIN BENEFIT_PLANS bp " +
            "ON e.BENEFIT_PLAN_ID  = bp.BENEFIT_PLANS_ID " +
            "WHERE  (:fullName IS NULL OR concat_ws(' ',e.CURRENT_FIRST_NAME ,e.CURRENT_MIDDLE_NAME , e.CURRENT_LAST_NAME) LIKE CONCAT('%', :fullName, '%')) " +
            "AND (:month IS NULL OR MONTH(e.BIRTH_DATE) = :month)",
            nativeQuery = true
    )
    Page<Personal> searchPersonalsByMonth(
            @Param("fullName") String fullName,
            @Param("month") Integer month,
            Pageable pageable
    );

//    SELECT *
//    FROM PERSONAL e
//    LEFT JOIN BENEFIT_PLANS bp
//    ON e.BENEFIT_PLAN_ID  = bp.BENEFIT_PLANS_ID
//    WHERE  (concat_ws(' ',e.CURRENT_FIRST_NAME ,e.CURRENT_MIDDLE_NAME , e.CURRENT_LAST_NAME))
//    LIKE '%Pound%'
//    AND e.BENEFIT_PLAN_ID = 1

//    concat_ws(' ',p.firstName,p.middleInitial, p.lastName)
}
