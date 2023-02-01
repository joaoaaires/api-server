package br.com.jaa.server.core.security;

import br.com.jaa.server.core.exceptio.ApiServerException;
import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.repositories.UsuarioCrudRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private SecurityHelper securityHelper;

    @Autowired
    private UsuarioLogged usuarioLogged;

    private final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

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

            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(SignatureAlgorithm.RS512.getMinKeyLength());

            KeyPair kp = keyGenerator.genKeyPair();
            PublicKey publicKey = (PublicKey) kp.getPublic();
            PrivateKey privateKey = (PrivateKey) kp.getPrivate();

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(tokenBase64)
                    .getBody();

            if (!id.equals(claims.getId())) {
                throw new ApiServerException("token não pertence a esse usuario");
            }

            securityHelper.gerarNovoToken(claims, usuario, response);

            usuarioLogged.set(usuario);

            List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ApiServerException ex) {
            logger.info("doFilterInternal", ex);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } finally {
            chain.doFilter(request, response);
        }
    }
}
