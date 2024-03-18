package com.example.ceom.service;


import com.example.ceom.entity.User;

import java.util.Optional;


public interface UserService {
     Optional<User> findByUsername(String username);
}
