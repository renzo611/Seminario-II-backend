package com.seminario.backend.controller;

import com.seminario.backend.dto.UserLoginRequestDto;
import com.seminario.backend.dto.UserLoginResponse;
import com.seminario.backend.exception.GeneralException;
import com.seminario.backend.services.IAuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final IAuthServices authServices;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> userLogin(@RequestBody() UserLoginRequestDto userLoginRequestDto) throws GeneralException {
        return new ResponseEntity<>(this.authServices.loginUser(userLoginRequestDto), HttpStatus.OK);
    }
}
