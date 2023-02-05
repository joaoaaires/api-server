package br.com.jaa.server.core.security;

import br.com.jaa.server.core.exceptio.ApiServerException;
import br.com.jaa.server.features.usuario.entities.Usuario;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class SecurityHelper {

    @Autowired
    private SecurityToken securityToken;

    public String[] getIdAndToken(String token) throws ApiServerException {
        //A REQUEST TEM O TOKEN DE ACESSO
        if (token == null) {
            throw new ApiServerException("token não encontrado");
        }

        //A REQUEST TEM O TOKEN MAS ESTA VAZIO
        if (token.trim().isEmpty()) {
            throw new ApiServerException("token está vazio");
        }

        String authorizationDecode = new String(Base64.getUrlDecoder().decode(token));
        String[] arrayAuthorization = authorizationDecode.split(":");

        //O AUTHORIZATION DEVE TER ID E TOKEN
        if (arrayAuthorization.length <= 1) {
            throw new ApiServerException("token incompleto");
        }

        return arrayAuthorization;
    }

    public void gerarNovoToken(Claims claims, Usuario usuario, HttpServletResponse httpResponse ) {
        Date dateLimit = claims.getExpiration();
        long diff = dateLimit.getTime() - System.currentTimeMillis();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if (days <= SecurityConst.DAYS_MINI_TOKEN) {
            securityToken.generateToken(usuario.getId(), usuario.getEmail(), usuario.getPassword(), httpResponse);
        }
    }

    public Claims decodeToken(String token, String passwordEncode) {
        return securityToken.decodeToken(token, passwordEncode);
    }

}

