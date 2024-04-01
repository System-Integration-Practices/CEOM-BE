package com.example.ceom.controller;

import com.example.ceom.entity.User;
import com.example.ceom.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {
    private UserService userService;

    @GetMapping("hello")
    public String user(){
        return "hello";
    }


//    @GetMapping("/user")
//    public ResponseEntity<Optional<User>> getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//
//        Optional<User> user = userService.findByUsername(username);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
