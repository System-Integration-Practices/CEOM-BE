package com.example.ceom.repository.sqlserver;

import com.example.ceom.entity.sqlserver.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Personal, Integer> {
    @Query(value = "SELECT p FROM PERSONAL p WHERE p.personalId = (SELECT MAX(p.personalId) FROM PERSONAL p)")
    Personal findLastPersonal();
}
