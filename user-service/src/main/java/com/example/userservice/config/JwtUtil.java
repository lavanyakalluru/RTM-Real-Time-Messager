package com.example.userservice.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "mySuperSecureJwtSecretKeyForMessengerProject123";

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(String phoneNumber) {

        return Jwts.builder()
                .setSubject(phoneNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(KEY)
                .compact();
    }
}