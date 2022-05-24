package ru.hse.equeue.service.jwt;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.hse.equeue.exception.UnauthorizedException;
import ru.hse.equeue.exception.message.ExceptionMessage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${android.clientId}")
    public List<String> clientId;

    public GoogleIdToken validateAndGetGoogleIdToken(String idTokenString) {
        GoogleIdTokenVerifier verifier =
                new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                        .setAudience(clientId)
                        .build();
        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);

            if (idToken != null) {
                return idToken;
            } else {
                throw new UnauthorizedException(ExceptionMessage.UNAUTHORIZED);
            }
        } catch (GeneralSecurityException | IOException e) {
            throw new UnauthorizedException(ExceptionMessage.UNAUTHORIZED);
        }
    }

    public String generateToken(String userId) {
        Date date = Date.from(LocalDate
                .now()
                .plusDays(15)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new UnauthorizedException(ExceptionMessage.UNAUTHORIZED);
        }
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
