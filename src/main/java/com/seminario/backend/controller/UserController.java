package com.seminario.backend.controller;

import com.seminario.backend.dto.ExistUserResponse;
import com.seminario.backend.dto.GeneralResponseDto;
import com.seminario.backend.dto.UserRegisterRequestDto;
import com.seminario.backend.exception.GeneralException;
import com.seminario.backend.services.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final IUserServices iUserServices;

    @PostMapping("/register")
    public ResponseEntity<GeneralResponseDto> registerNewUser(@RequestBody() UserRegisterRequestDto userRegisterRequestDto) throws GeneralException {
        return new ResponseEntity<>(iUserServices.registerUser(userRegisterRequestDto), CREATED);
    }

    @GetMapping("/exist/{email}")
    public ResponseEntity<ExistUserResponse> existUser(@PathVariable String email){
        return new ResponseEntity<>(iUserServices.existUser(email), OK);
    }
}
