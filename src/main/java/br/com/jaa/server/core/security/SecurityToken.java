package br.com.jaa.server.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.security.*;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Component
public class SecurityToken {

    public void generateToken(Long id, String email, String passwordEncode, HttpServletResponse httpResponse) {
        this.generateToken(String.valueOf(id), email, passwordEncode, httpResponse);
    }

    public void generateToken(String id, String email, String passwordEncode, HttpServletResponse httpResponse) {
        long now = System.currentTimeMillis();
        Date dateNow = new Date(now);
        Date dateLimit = new Date(now);

        // ADICIONAR 10 DIAS NO TEMPO LIMIT
        Calendar c = Calendar.getInstance();
        c.setTime(dateLimit);
        c.add(Calendar.DATE, SecurityConst.DAYS_EXPIRATION);
        dateLimit = c.getTime();

        //GERAR TOKEN
        String token = Jwts.builder()
                .setId(id)
                .setSubject(email)
                .setIssuedAt(dateNow)
                .setExpiration(dateLimit)
                .signWith(getSignKey(passwordEncode), SignatureAlgorithm.HS256)
                .compact();

        String idAndToken = id + ":" + token;
        String encodedIdAndToken = Base64.getEncoder().encodeToString(idAndToken.getBytes());

        httpResponse.addHeader(SecurityConst.KEY_AUTH_RESPONSE, encodedIdAndToken);
    }

    public Claims decodeToken(String token, String passwordEncode) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey(passwordEncode))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey(String passwordEncode) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = md.digest(passwordEncode.getBytes());
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
