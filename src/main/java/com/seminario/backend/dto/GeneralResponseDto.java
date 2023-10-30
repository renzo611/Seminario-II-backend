package com.seminario.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralResponseDto {
    private long code;
    private String message;

    public GeneralResponseDto(long code, String message) {
        this.code = code;
        this.message = message;
    }
}
