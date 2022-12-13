package br.com.jaa.server.core.security;

import br.com.jaa.server.core.exceptio.ApiServerException;
import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.repositories.UsuarioCrudRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SecurityFilter extends BasicAuthenticationFilter {

    private final UsuarioCrudRepository usuarioCrudRepository;
    private final SecurityHelper securityHelper;

    public SecurityFilter(
            AuthenticationManager authenticationManager,
            UsuarioCrudRepository usuarioCrudRepository,
            SecurityHelper securityHelper
    ) {
        super(authenticationManager);
        this.usuarioCrudRepository = usuarioCrudRepository;
        this.securityHelper = securityHelper;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        try {

            String token = request.getHeader("Authorization");

            String[] values = securityHelper.getIdAndToken(token);

            String id = values[0];
            String tokenBase64 = values[1];

            Optional<Usuario> optionalUsuario = usuarioCrudRepository.findById(Long.valueOf(id));
            if (optionalUsuario.isEmpty()) {
                throw new ApiServerException("usuario não encontrado");
            }

            Usuario usuario = optionalUsuario.get();

//            Claims claims = Jwts.parser()
//                    .setSigningKey(usuario.getPassword().getBytes())
//                    .parseClaimsJws(tokenBase64)
//                    .getBody();

//            if (!id.equals(claims.getId())) {
//                throw new ApiServerException("token não pertence a esse usuario");
//            }
//
//            securityHelper.gerarNovoToken(claims, usuario, response);

            List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ApiServerException ex) {
            ex.printStackTrace();
        } finally {
            chain.doFilter(request, response);
        }
    }
}
