package com.seminario.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;


@Service
public class JWTUtils {
    private Algorithm algorithm;
    private JWTVerifier verifier;
    private static final long EXPIRATION_TIME_OFFSET = 1000 *60 * 60 * 10L;

    @PostConstruct
    private void init() {
        algorithm = Algorithm.HMAC256("test_encode");
        verifier = JWT.require(algorithm).build();
    }

    public String generateToken(UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_OFFSET))
                .sign(algorithm);
    }

    public boolean validateToken(String token, UserDetails userDetails){
        DecodedJWT decodedJWT = verifier.verify(token);
        Date date = new Date();

        return userDetails.getUsername().equals(decodedJWT.getSubject())
                && decodedJWT.getIssuedAt().before(date)
                && decodedJWT.getExpiresAt().after(date);
    }

    public String extractUsername(String token) {
        return verifier.verify(token).getSubject();
    }
}
