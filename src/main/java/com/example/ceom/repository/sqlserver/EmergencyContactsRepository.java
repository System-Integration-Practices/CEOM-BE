package com.example.ceom.repository.sqlserver;

import com.example.ceom.model.sqlserver.EmergencyContacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactsRepository extends JpaRepository<EmergencyContacts, Integer> {
}
