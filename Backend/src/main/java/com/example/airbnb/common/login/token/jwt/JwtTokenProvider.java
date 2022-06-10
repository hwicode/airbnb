package com.example.airbnb.common.login.token.jwt;


import com.example.airbnb.common.login.token.TokenProvider;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static com.example.airbnb.common.login.token.jwt.TokenStatus.*;

@Component
public class JwtTokenProvider implements TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static final long FIFTEEN_MINUTE = 1000 * 60 * 10;
    public static final long ONE_WEEK = 1000 * 60 * 12 * 24 * 7;

    private Key getSigninKey() {
        return key;
    }

    public String createToken(String githubId, long expire) {
        Claims claims = Jwts.claims();
        claims.put("githubId", githubId);
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (expire)))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getAttribute(String token) {
        String parsedToken = parse(token);
        return getClaims(parsedToken).get("githubId").toString();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public TokenStatus validateToken(String token) {
        String parsedToken;
        try {
            parsedToken = parse(token);
            Claims claims = getClaims(parsedToken);
            return VALID;
        } catch (SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
            return INVALID;
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
            return EXPIRED;
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
            return INVALID;
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
            return INVALID;
        } catch (Exception e) {
            logger.info("신뢰할 수 없는 토큰입니다.");
        }
        return INVALID;
    }

    private String parse(String token) {
        return token.split(" ")[1];
    }
}
