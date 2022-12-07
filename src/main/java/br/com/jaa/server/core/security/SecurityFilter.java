package br.com.jaa.server.core.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SecurityFilter extends BasicAuthenticationFilter {


    @Autowired
    public SecurityFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
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

//            List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                    "admin",
//                    null,
//                    authorities
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }catch (ApiServerException ex) {
//            ex.printStackTrace();
        } finally {
            chain.doFilter(request, response);
        }
    }
}
