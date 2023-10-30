package com.seminario.backend.services.impl;

import com.seminario.backend.models.Users;
import com.seminario.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService") @RequiredArgsConstructor
public class UserDetailService  implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = this.userRepository.findById(Integer.parseInt(username)).orElse(null);

        if (users == null) {
            throw new UsernameNotFoundException("User not found in DB");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();


        return new User(users.getId().toString(), users.getPassword(), authorities);
    }
}
