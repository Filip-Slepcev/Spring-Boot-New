package com.springboot.blog.springbootblogrestapi.security;

import com.springboot.blog.springbootblogrestapi.exception.BlogAPIException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.security.Key;
import java.util.Date;

import static io.jsonwebtoken.security.Keys.secretKeyFor;

@Component
public class JwtTokenProvider {

    private final Key JWT_SECRET = secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationInMs;

    // generate token
    public String generateToken(@NotNull Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(System.currentTimeMillis()+1000*60*60*24);

        String token = Jwts.builder().setSubject(username).setIssuedAt(currentDate).setExpiration(expireDate).signWith(JWT_SECRET).compact();
        return token;
    }

    // get username from the token
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // validate JWt token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }
}
