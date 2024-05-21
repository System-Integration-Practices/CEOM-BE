package com.example.ceom.response;

import com.example.ceom.model.auth.Role;
import com.example.ceom.model.auth.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("username")
    private String userName;

    @JsonProperty("is_active")
    private boolean active;

    @JsonProperty("role")
    private Role role;
    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .userName(user.getUsername())
                .active(user.isActive())
                .role(user.getRole())
                .build();
    }
}
