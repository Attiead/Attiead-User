package com.example.userservice.common.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenProvider {

  @Value("${security.jwt.expired}")
  private static long jwtExpired;

  public static String createToken(String uid) {

    Instant issueAt = Instant.now();

    // Prepare the JWT builder
    JwtBuilder jwtBuilder = Jwts.builder()
        .setIssuedAt(Date.from(issueAt))
        .setExpiration(Date.from(issueAt.plus(jwtExpired, ChronoUnit.MINUTES)));

    // Prepare the signing key
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    Key signingKey = Keys.hmacShaKeyFor(uid.getBytes());

    // Sign and build the JWT
    return jwtBuilder
        .signWith(signingKey, signatureAlgorithm)
        .compact(); // JWT 토큰 생성
  }
}
