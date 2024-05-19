package com.example.ceom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("username")
    @NotBlank(message = "Phone number is required")
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @JsonProperty("retype_password")
    private String retypePassword;

    @NotNull(message = "Role ID is required")
    @JsonProperty("role_id")
    private Long roleId;
}
