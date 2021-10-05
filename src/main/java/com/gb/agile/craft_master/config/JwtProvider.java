package com.gb.agile.craft_master.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@Log
public class JwtProvider {

    private static final String JWT_SECRET = "kjiugfdkmdrdswq";
    private static final String USERID_CLAIM = "id";
    private static final String LOGIN_CLAIM = "login";
    private static final String ROLE_CLAIM = "role";

    private static final ThreadLocal<Long> userId = new ThreadLocal<>();

    public String generateToken(Long userId, String login, String role) {
        Instant expirationTime = Instant.now().plus(60, ChronoUnit.DAYS);
        Date expirationDate = Date.from(expirationTime);

        String compactTokenString = Jwts.builder()
                .claim(USERID_CLAIM, userId)
                .claim(LOGIN_CLAIM, login)
                .claim(ROLE_CLAIM, role)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        return "Bearer " + compactTokenString;
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
            userId.set(claims.get(USERID_CLAIM, Long.class));
            return true;
        } catch (Exception e) {
            log.severe("invalid token");
            userId.set(null);
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().get(LOGIN_CLAIM, String.class);
    }

    public String getRoleFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().get(ROLE_CLAIM, String.class);
    }

    // пока пустой метод, чтобы не возиться с хранением вылогинившихся токенов
    public void setTokenLogout(String token) {
//        Date expDate = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getExpiration();
//        Duration duration = Duration.between(Instant.now(), expDate.toInstant());
//        tokenRedisRepository.putToken(token, duration);
    }

    public static Long getUserId() {
        return userId.get();
    }
}
