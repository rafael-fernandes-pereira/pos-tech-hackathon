package com.github.rafaelfernandes.users.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.github.rafaelfernandes.users.enums.UserRoles;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

     public static final String SECRET = "66e48fcca777ed0975ff8a7f51198db678aea9661298bcd34adace1ecefa2cce";

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        Claims claims = this.getAllClaimsFromToken(token);
        Date createdDate = claims.getIssuedAt();
        Date now = new Date();
        return now.getTime() - createdDate.getTime() > 120000;
    }

    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

    public String generateToken(String email, UserRoles userRole, UUID id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userRole);

        if (userRole == UserRoles.CUSTOMER) {
            claims.put("userId", id);
        }

        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 120000))
                .signWith(this.key, SignatureAlgorithm.HS256).compact();
    }


}
