package com.seminario.backend.dto;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String jwt;
    private Integer userId;

    public UserLoginResponse(String jwt, Integer userId) {
        this.jwt = jwt;
        this.userId = userId;
    }
}
