package com.example.ceom.service;





import com.example.ceom.entity.mysql.User;

import java.util.Optional;


public interface UserService {
     Optional<User> findByUsername(String username);
}
