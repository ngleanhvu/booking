package com.ngleanhvu.auth_service.util;

import com.ngleanhvu.auth_service.entity.Auth;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class JwtUtil {
    private static final long ACCESS_TOKEN_EXPIRATION = 3600; // 1 giờ
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 3600; // 7 ngày

    @Value("${host_name}")
    private String hostName;

    @Value("${server.port}")
    private String port;

    @Value("${protocol}")
    private String protocol;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @PostConstruct
    public void init() throws Exception {
        privateKey = RsaKeyUtil.getPrivateKey();
        publicKey = RsaKeyUtil.getPublicKey();
    }

    public String generateAccessToken(Auth auth) throws Exception {
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(String.valueOf(auth.getId()))
                .setIssuer("auth-service")
                .setAudience("gateway")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(ACCESS_TOKEN_EXPIRATION)))
                .claim("email", auth.getEmail())
                .claim("role", auth.getRole().name())
                .claim("scope", "openid profile email")
                .claim("jti", UUID.randomUUID().toString())
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public String generateRefreshToken(Auth auth) throws Exception {
        Instant now = Instant.now();

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(String.valueOf(auth.getId())) // sub
                .claim("type", "refresh_token")
                .setIssuedAt(Date.from(now))              // iat
                .setExpiration(Date.from(now.plusSeconds(REFRESH_TOKEN_EXPIRATION))) // exp
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {

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