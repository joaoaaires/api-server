package br.com.jaa.server.core.security;

import br.com.jaa.server.core.exceptio.ApiServerException;
import br.com.jaa.server.features.usuario.repositories.UsuarioCrudRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

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

            /*
            List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    "admin",
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            */
        } catch (ApiServerException ex) {
            ex.printStackTrace();
        } finally {
            chain.doFilter(request, response);
        }
    }

    /*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

            /*



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




    }
    */
}
