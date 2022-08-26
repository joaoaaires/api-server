package br.com.jaa.server.core.security;

import br.com.jaa.server.core.exceptio.ApiServerException;
import br.com.jaa.server.core.util.ValidationUtil;
import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.repositories.UsuarioCrudRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SecurityFilter extends BasicAuthenticationFilter {


    @Autowired
    public SecurityFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse,
            FilterChain chain
    ) throws IOException, ServletException {
        try {
            System.out.println("Authorization: token");

            /*
            String token = httpRequest.getHeader("Authorization");

            System.out.println("Authorization: " + token);

            String[] values = securityHelper.getIdAndToken(token);

            String id = values[0];
            String tokenBase64 = values[1];

            Optional<Usuario> optionalUsuario = usuarioCrudRepository.findById(Long.valueOf(id));
            if (optionalUsuario.isEmpty()) {
                throw new ApiServerException("usuario não encontrado");
            }

            Usuario usuario = optionalUsuario.get();

            Claims claims = Jwts.parser()
                    .setSigningKey(usuario.getPassword().getBytes())
                    .parseClaimsJws(tokenBase64)
                    .getBody();

            if (!id.equals(claims.getId())) {
                throw new ApiServerException("token não pertence a esse usuario");
            }

            securityHelper.gerarNovoToken(claims, usuario, httpResponse);

            usuarioLogged.set(usuario);
            */

            List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    "admin",
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }catch (ApiServerException ex) {
//            ex.printStackTrace();
        } finally {
            chain.doFilter(httpRequest, httpResponse);
        }
    }

}
