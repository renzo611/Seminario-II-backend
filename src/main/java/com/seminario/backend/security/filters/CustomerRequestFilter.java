package com.seminario.backend.security.filters;

import com.seminario.backend.services.impl.UserDetailService;
import com.seminario.backend.utils.JWTUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.seminario.backend.security.constant.SecurityConstants.AUTHORIZATION;
import static com.seminario.backend.security.constant.SecurityConstants.BEARER_PREFIX;

@Component
@RequiredArgsConstructor
public class CustomerRequestFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;
    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (SecurityContextHolder.getContext().getAuthentication() == null && headersValidation(authorizationHeader)) {
            String jwt = authorizationHeader.substring(BEARER_PREFIX.length());
            String username = jwtUtils.extractUsername(jwt);

            if (username != null) {
                UserDetails userDetails = userDetailService.loadUserByUsername(username);

                if (jwtUtils.validateToken(jwt, userDetails)) {
                    var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean headersValidation(String authHeader) {
        return authHeader != null && authHeader.startsWith(BEARER_PREFIX);
    }
}