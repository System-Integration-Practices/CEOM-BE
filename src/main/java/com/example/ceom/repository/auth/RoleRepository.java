package com.example.ceom.repository.auth;

import com.example.ceom.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
