package com.seminario.backend.services;

import com.seminario.backend.dto.UserLoginRequestDto;
import com.seminario.backend.dto.UserLoginResponse;
import com.seminario.backend.exception.GeneralException;

public interface IAuthServices {
    UserLoginResponse loginUser(UserLoginRequestDto userLoginRequestDto) throws GeneralException;
}
