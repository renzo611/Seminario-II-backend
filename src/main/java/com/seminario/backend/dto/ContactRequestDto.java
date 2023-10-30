package com.seminario.backend.dto;

import lombok.Data;

@Data
public class ContactRequestDto {
    private Integer userId;
    private String name;
    private String email;
    private String phone;
}
