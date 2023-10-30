package com.seminario.backend.services.impl;

import com.seminario.backend.dto.ExistUserResponse;
import com.seminario.backend.dto.GeneralResponseDto;
import com.seminario.backend.dto.UserRegisterRequestDto;
import com.seminario.backend.exception.GeneralException;
import com.seminario.backend.models.Users;
import com.seminario.backend.repository.UserRepository;
import com.seminario.backend.services.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServices implements IUserServices {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public GeneralResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) throws GeneralException {
        Users users = this.userRepository.findByEmailOrUsername( userRegisterRequestDto.getEmail(),
                                                               userRegisterRequestDto.getUsername()).orElse(null);

        if(users != null){
            throw new GeneralException("El usuario ya se encuentra registrado", 404);
        }

        String passwordEncode = this.passwordEncoder.encode(userRegisterRequestDto.getPassword());

        Users usersEntity = this.userRepository.save(new Users(
                userRegisterRequestDto.getName(),
                userRegisterRequestDto.getEmail(),
                userRegisterRequestDto.getUsername(),
                passwordEncode
        ));

        if(usersEntity.getId() == null || usersEntity.getId() <= 0){
            throw  new GeneralException("Error guardando nuevo usuario", 500);
        }

        return new GeneralResponseDto(201, "User created");
    }

    @Override
    public ExistUserResponse existUser(String email) {
        boolean existUser = this.userRepository.existsByEmail(email);

        return  new ExistUserResponse(existUser);
    }
}
