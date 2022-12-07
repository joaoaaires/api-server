package br.com.jaa.server.core.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

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
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String token = Jwts.builder().setId(id).setIssuedAt(dateNow).setSubject(email).setExpiration(dateLimit)
                .signWith(key).compact();

        String idAndToken = id + ":" + token;
        String encodedIdAndToken = Base64.getEncoder().encodeToString(idAndToken.getBytes());

        httpResponse.addHeader(SecurityConst.KEY_AUTH_RESPONSE, encodedIdAndToken);
    }

    public void generateToken(Authentication authentication, HttpServletResponse httpResponse) {
//        Instant now = Instant.now();
//        String scope = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(" "));
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .issuer("self")
//                .issuedAt(now)
//                .expiresAt(now.plus(SecurityConst.DAYS_EXPIRATION, ChronoUnit.DAYS))
//                .subject(authentication.getName())
//                .claim("scope", scope)
//                .build();
//
////        String token = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
//        String token = "";
//
//        String idAndToken = 0 + ":" + token;
//        String encodedIdAndToken = Base64.getEncoder().encodeToString(idAndToken.getBytes());
//
//        httpResponse.addHeader(SecurityConst.KEY_AUTH_RESPONSE, encodedIdAndToken);
    }

}
