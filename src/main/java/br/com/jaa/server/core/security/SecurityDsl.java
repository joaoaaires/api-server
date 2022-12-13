package br.com.jaa.server.core.security;

import br.com.jaa.server.features.usuario.repositories.UsuarioCrudRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class SecurityDsl extends AbstractHttpConfigurer<SecurityDsl, HttpSecurity> {
    private final UsuarioCrudRepository usuarioCrudRepository;
    private final SecurityHelper securityHelper;

    public SecurityDsl(
            UsuarioCrudRepository usuarioCrudRepository,
            SecurityHelper securityHelper
    ) {
        this.usuarioCrudRepository = usuarioCrudRepository;
        this.securityHelper = securityHelper;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new SecurityFilter(
                authenticationManager,
                usuarioCrudRepository,
                securityHelper
        ));
    }

    public static SecurityDsl securityDsl(
            UsuarioCrudRepository usuarioCrudRepository,
            SecurityHelper securityHelper
    ) {
        return new SecurityDsl(
                usuarioCrudRepository,
                securityHelper
        );
    }
}
