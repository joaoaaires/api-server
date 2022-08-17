//package br.com.jaa.server.core.security;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.Base64;
//import java.util.Calendar;
//import java.util.Date;
//
//@Component
//public class SecurityToken {
//
//    public void generateToken(Long id, String email, String passwordEncode, HttpServletResponse httpResponse) {
//        this.generateToken(String.valueOf(id), email, passwordEncode, httpResponse);
//    }
//
//    public void generateToken(String id, String email, String passwordEncode, HttpServletResponse httpResponse) {
//        long now = System.currentTimeMillis();
//        Date dateNow = new Date(now);
//        Date dateLimit = new Date(now);
//
//        // ADICIONAR 10 DIAS NO TEMPO LIMIT
//        Calendar c = Calendar.getInstance();
//        c.setTime(dateLimit);
//        c.add(Calendar.DATE, SecurityConst.DAYS_EXPIRATION);
//        dateLimit = c.getTime();
//
//        //GERAR TOKEN
//        String token = Jwts.builder().setId(id).setIssuedAt(dateNow).setSubject(email).setExpiration(dateLimit)
//                .signWith(SignatureAlgorithm.HS256, passwordEncode.getBytes()).compact();
//
//        String idAndToken = id + ":" + token;
//        String encodedIdAndToken = Base64.getEncoder().encodeToString(idAndToken.getBytes());
//
//        httpResponse.addHeader(SecurityConst.KEY_AUTH_RESPONSE, encodedIdAndToken);
//    }
//
//}
