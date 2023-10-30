package com.seminario.backend.services.impl;

import com.seminario.backend.dto.UserLoginRequestDto;
import com.seminario.backend.dto.UserLoginResponse;
import com.seminario.backend.exception.GeneralException;
import com.seminario.backend.models.Users;
import com.seminario.backend.repository.UserRepository;
import com.seminario.backend.services.IAuthServices;
import com.seminario.backend.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServices implements IAuthServices {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserLoginResponse loginUser(UserLoginRequestDto userLoginRequestDto) throws GeneralException {
        Users user = this.userRepository.findByEmailOrUsername( userLoginRequestDto.getUsername(), userLoginRequestDto.getUsername()).
                     orElseThrow(() -> new GeneralException("The user don't exist", 403));

        if(!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())){
            throw new GeneralException("Password does match", 403);
        }

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getId().toString(), userLoginRequestDto.getPassword())
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        return new UserLoginResponse(jwtUtils.generateToken(userDetails), user.getId());
    }
}
