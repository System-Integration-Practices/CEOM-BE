package com.example.ceom.repository.sqlserver;

import com.example.ceom.model.sqlserver.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
