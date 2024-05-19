package com.example.ceom.repository.auth;

import com.example.ceom.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
    //SELECT * FROM users WHERE phoneNumber=?
}

