package br.com.jaa.server.core.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
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
        try {
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(SignatureAlgorithm.RS512.getMinKeyLength());

            KeyPair kp = keyGenerator.genKeyPair();
            PublicKey publicKey = (PublicKey) kp.getPublic();
            PrivateKey privateKey = (PrivateKey) kp.getPrivate();

            String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());

            String token = Jwts.builder()
                    .setId(id)
                    .setSubject(email)
                    .setIssuedAt(dateNow)
                    .setExpiration(dateLimit)
                    .signWith(privateKey, SignatureAlgorithm.RS512)
                    .compact();

            String idAndToken = id + ":" + token;
            String encodedIdAndToken = Base64.getEncoder().encodeToString(idAndToken.getBytes());

            httpResponse.addHeader(SecurityConst.KEY_AUTH_RESPONSE, encodedIdAndToken);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
