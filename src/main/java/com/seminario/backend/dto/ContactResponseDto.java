package com.seminario.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactResponseDto {
    private Integer id;

    private String name;

    private String email;

    private String phone;
}
