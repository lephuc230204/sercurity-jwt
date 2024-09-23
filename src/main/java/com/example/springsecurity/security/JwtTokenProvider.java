package com.example.springsecurity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springsecurity.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final UserDetailsServiceImpl userDetailsService;

    // Generate access token
    public String generateAccessToken(Authentication auth) {
        return JWT.create()
                .withExpiresAt(Instant.now().plusMillis(jwtProperties.getAccessExpiresAt()))
                .withSubject(auth.getName())
                .withClaim("role", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse(null))
                .sign(Algorithm.HMAC512(jwtProperties.getAccessSecret()));
    }

    // Generate refresh token
    public String generateRefreshToken(Authentication auth) {
        return JWT.create()
                .withExpiresAt(Instant.now().plusMillis(jwtProperties.getRefreshExpiresAt()))
                .withIssuedAt(Instant.now())
                .withSubject(auth.getName())
                .sign(Algorithm.HMAC512(jwtProperties.getRefreshSecret()));
    }

    // Validate access token
    public boolean validateAccessToken(String token) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(jwtProperties.getAccessSecret()))
                    .acceptExpiresAt(jwtProperties.getAccessExpiresAt())
                    .withClaimPresence("sub")
                    .withClaimPresence("role")
                    .build()
                    .verify(token);

            String username = jwt.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!userDetails.isEnabled()) {
                return false;
            }

            String tokenRole = jwt.getClaim("role").asString();
            if (tokenRole == null) {
                return false;
            }

            return userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> role.equals(tokenRole));
        } catch (Exception e) {
            return false;
        }
    }

    // Validate refresh token
    public boolean validateRefreshToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(jwtProperties.getRefreshSecret()))
                    .acceptExpiresAt(jwtProperties.getRefreshExpiresAt())
                    .withClaimPresence("sub")
                    .withClaimPresence("iat")
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // Create an Authentication object from the token
    public Authentication createAuthentication(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String username = jwt.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }
}
