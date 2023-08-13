package com.example.userservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenProvider {

    public String createToken(UUID uid) {

        // Prepare the JWT builder
        io.jsonwebtoken.JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000));

        // Prepare the signing key
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = Keys.hmacShaKeyFor(uid.toString().getBytes());

        // Sign and build the JWT
        return jwtBuilder
                .signWith(signingKey, signatureAlgorithm)
                .compact(); // JWT 토큰 생성
    }
}
