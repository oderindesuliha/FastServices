package org.group6.fastservices.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.group6.fastservices.data.models.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Value("${EXPIRY_SECONDS}")
    private long jwtExpirationDate;

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Extract role from authorities and map to Role enum
        Role role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .map(String::toUpperCase)
                .map(Role::valueOf)
                .orElse(Role.CUSTOMER);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", role.name()) // store enum name as claim
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(jwtExpirationDate)))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        try {
            return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        } catch (Exception e) {
            return Keys.hmacShaKeyFor(jwtSecret.getBytes());
        }
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Role getRole(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String role = claims.get("role", String.class);
        return Role.valueOf(role);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
