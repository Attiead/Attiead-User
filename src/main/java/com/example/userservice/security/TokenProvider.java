package com.example.userservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenProvider {

    public String createToken(UUID uid) {

        Instant issueAt = Instant.now();

        // Prepare the JWT builder
        io.jsonwebtoken.JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(Date.from(issueAt))
                .setExpiration(Date.from(issueAt.plus(1, ChronoUnit.MINUTES)));

        // Prepare the signing key
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = Keys.hmacShaKeyFor(uid.toString().getBytes());

        // Sign and build the JWT
        return jwtBuilder
                .signWith(signingKey, signatureAlgorithm)
                .compact(); // JWT 토큰 생성
    }
}
