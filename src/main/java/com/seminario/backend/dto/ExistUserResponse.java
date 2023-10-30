package com.seminario.backend.dto;

import lombok.Data;

@Data
public class ExistUserResponse {
    boolean existUser;

    public ExistUserResponse(boolean existUser) {
        this.existUser = existUser;
    }
}
