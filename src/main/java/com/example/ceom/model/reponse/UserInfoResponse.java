package com.example.ceom.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseCookie;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
//    private long id;

    private String username;

    private ResponseCookie jwtCookie;

//    private String email;
//
    private List<String> roles;
}
