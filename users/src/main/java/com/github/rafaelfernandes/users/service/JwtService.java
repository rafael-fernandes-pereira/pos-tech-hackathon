package com.github.rafaelfernandes.users.service;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.rafaelfernandes.users.enums.UserRoles;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

     public static final String SECRET = "66e48fcca777ed0975ff8a7f51198db678aea9661298bcd34adace1ecefa2cce";


    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(this.getSignKey()).build().parseClaimsJws(token);
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
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(this.getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
