package com.ngleanhvu.auth_service.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    private static final long ACCESS_TOKEN_EXPIRATION = 3600; // 1 giờ
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 3600; // 7 ngày

    // Generate access token (ngắn hạn)
    public String generateAccessToken(String subject) throws Exception {
        RSAPrivateKey privateKey = RsaKeyUtil.getPrivateKey();
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(subject)
                .setIssuer("http://localhost:8080")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(ACCESS_TOKEN_EXPIRATION)))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    // Generate refresh token (dài hạn)
    public String generateRefreshToken(String subject) throws Exception {
        RSAPrivateKey privateKey = RsaKeyUtil.getPrivateKey();
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(subject)
                .setIssuer("http://localhost:8080")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(REFRESH_TOKEN_EXPIRATION)))
                .claim("type", "refresh_token")
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            RSAPublicKey publicKey = RsaKeyUtil.getPublicKey();

            Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Malformed JWT token: {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public String getSubject(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(RsaKeyUtil.getPublicKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(RsaKeyUtil.getPublicKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
}