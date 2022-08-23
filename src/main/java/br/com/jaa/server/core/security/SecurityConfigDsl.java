package br.com.jaa.server.core.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfigDsl extends AbstractHttpConfigurer<SecurityConfigDsl, HttpSecurity> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http
                .addFilter(new SecurityFilter(authenticationManager))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    public static SecurityConfigDsl getInstance() {
        return new SecurityConfigDsl();
    }

}
