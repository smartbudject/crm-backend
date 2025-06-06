package ru.smartbudject.crmbackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


/**
 * Сервис для работы с jwt.
 */
@Service
public class JwtService {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Генерация секретного ключа
    private static final long EXPIRATION_TIME = 86400000; // 24 часа


    /**
     * Генерация токена.
     * @param email
     * @param roleName
     * @param username
     * @return jwt
     */
    public String generateToken(final String email, final String roleName, final String username) {
        final Map<String, Object> claims = Map.of("email", email, "role", roleName);
        return createToken(claims, username);
    }


    /**
     * Создания токена.
     * @param claims
     * @param subject
     * @return jwt
     */
    private String createToken(final Map<String, Object> claims, final String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }


    /**
     * Валидация токена.
     * @param token
     * @param userDetails
     * @return boolean
     */
    public Boolean validateToken(final String token, final UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    /**
     * Вытаскиваем имя пользователя.
     * @param token
     * @return username
     */
    public String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }


    /**
     * Вытаскиваем дату срока токена.
     * @param token
     * @return data
     */
    public Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    /**
     * Вытаскиваем токен.
     * @param token
     * @param claimsResolver
     * @param <T>
     * @return claims
     */
    private <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    /**
     * Метод для получения claims.
     * @param token
     * @return claims
     */
    private Claims extractAllClaims(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * Метод проверки срока токена.
     * @param token
     * @return boolean
     */
    private Boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

}
