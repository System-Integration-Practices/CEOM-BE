package com.example.ceom.repository.sqlserver;

import com.example.ceom.entity.sqlserver.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Personal, Integer> {
}
