package com.example.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private static final String SECRET =
            "mySuperSecureJwtSecretKeyForMessengerProject123";

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public boolean validateToken(String token) {

        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            System.out.println("JWT validation error: " + e.getMessage());
            return false;
        }
    }

    public String extractPhone(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}