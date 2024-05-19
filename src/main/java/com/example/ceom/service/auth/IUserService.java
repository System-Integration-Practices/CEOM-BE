package com.example.ceom.service.auth;


import com.example.ceom.dto.UserDTO;
import com.example.ceom.model.auth.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;
    String login(String phoneNumber, String password, Long roleId) throws Exception;
    User getUserDetailsFromToken(String token) throws Exception;
}
