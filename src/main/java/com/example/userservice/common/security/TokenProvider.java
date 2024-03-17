package com.example.userservice.common.security;

import com.example.userservice.common.exception.InvalidJwtTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {

  @Value("${security.jwt.expired}")
  private static long jwtExpired;

  @Value("${security.jwt.secretkey}")
  private static String jwtSecretkey;

  private static ObjectMapper objectMapper;

  public static String createToken(String uid) {

    Instant issueAt = Instant.now();

    // Prepare the JWT builder
    JwtBuilder jwtBuilder = Jwts.builder()
        .setIssuedAt(Date.from(issueAt))
        .setExpiration(Date.from(issueAt.plus(jwtExpired, ChronoUnit.MINUTES)));

    // Prepare the signing key
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    Key signingKey = Keys.hmacShaKeyFor(jwtSecretkey.getBytes(StandardCharsets.UTF_8));

    // Sign and build the JWT
    return jwtBuilder
        .claim("uid", uid)
        .signWith(signingKey, signatureAlgorithm)
        .compact(); // JWT 토큰 생성
  }

  public static String validToken(String jwt) {
    try {
      SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecretkey.getBytes(StandardCharsets.UTF_8));

      return (String) Jwts.parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(jwt)
          .getBody()
          .get("uid");

    } catch (Exception exception) {
      throw new InvalidJwtTokenException("Invalid Token");
    }
  }

}
