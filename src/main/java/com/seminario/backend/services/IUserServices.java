package com.seminario.backend.services;

import com.seminario.backend.dto.ExistUserResponse;
import com.seminario.backend.dto.GeneralResponseDto;
import com.seminario.backend.dto.UserRegisterRequestDto;
import com.seminario.backend.exception.GeneralException;

public interface IUserServices {
    GeneralResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) throws GeneralException;

    ExistUserResponse existUser(String email);
}
