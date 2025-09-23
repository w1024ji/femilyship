package com.example.femilyship.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64; // ◀◀◀ Import 추가
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    // 1. @Value 어노테이션으로 properties 파일의 값을 주입받습니다.
    @Value("${jwt.secret.key}")
    private String secretKey;

    // 2. Key 객체를 멤버 변수로 선언합니다.
    private Key key;

    // 3. 생성자 대신 @PostConstruct를 사용하여 의존성 주입 후 키를 초기화합니다.
    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // ▼▼▼▼▼ 기존 코드는 삭제합니다 ▼▼▼▼▼
    // private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    // ▲▲▲▲▲ 이 라인을 삭제하세요 ▲▲▲▲▲

    private final long validityInMilliseconds = 3600000; // 1 hour

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        // 이제 init()에서 생성된 고정된 key를 사용합니다.
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS512) // 알고리즘을 명시해주는 것이 좋습니다.
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key) // 동일한 key를 사용합니다.
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken); // 동일한 key를 사용합니다.
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }
}
