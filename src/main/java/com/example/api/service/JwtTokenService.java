package com.example.api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtTokenService {
    @Value("${secret.key}")
    private String SECRET_KEY;
    private static final ConcurrentHashMap<String, String> nonces = new ConcurrentHashMap<>();

    public String generateToken(String email, String pin) {
        invalidatePreviousNonce(email);
        String nonce = UUID.randomUUID().toString();
        nonces.put(email, nonce);
        return Jwts.builder()
                .setSubject(email)
                .claim("nonce", nonce)
                .claim("pin", pin)
                .setExpiration(new Date(System.currentTimeMillis() +  60 * 60 * 1000)) // 1 hour expiration
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public boolean isValidUrl(String token) {
        String email = extractEmailFromToken(token);
        Claims claims = extractAllClaims(token);
        return nonces.containsKey(email) && claims.get("nonce").equals(nonces.get(email)) &&
                System.currentTimeMillis() <= claims.getExpiration().getTime();
    }
    public String extractPinFromToken(String token) {
            Claims claims = extractAllClaims(token);
            return (String) claims.get("pin");
    }
    public String extractEmailFromToken(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()
                .parseClaimsJws(token).getBody();
    }

    public void invalidatePreviousNonce(String email) {
        nonces.remove(email);
    }
}