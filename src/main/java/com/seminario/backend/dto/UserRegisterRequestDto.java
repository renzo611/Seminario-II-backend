package com.seminario.backend.dto;

import lombok.Data;

@Data
public class UserRegisterRequestDto {
    private String name;
    private String email;
    private String username;
    private String password;
}
