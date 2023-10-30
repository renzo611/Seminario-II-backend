package com.seminario.backend.repository;

import com.seminario.backend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmailOrUsername(String email, String username);
    boolean existsByEmail(String email);
}
