package com.example.airbnb.common.login.token;


import com.example.airbnb.common.login.token.github.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider implements TokenProvider {

    private static final long EXPIRE = 60 * 1000;

    @Value("${oauth.token.secret}")
    private String secretKey;

    private long tokenValidationSeconds;

    public JwtTokenProvider(String secretKey, long tokenValidationSeconds) {
        this.secretKey = secretKey;
        this.tokenValidationSeconds = tokenValidationSeconds;
    }

    public JwtTokenProvider() {
    }

    private Key getSigninKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createJwtToken(String githubId) {
        Claims claims = Jwts.claims();
        claims.put("githubId", githubId);
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (EXPIRE)))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
