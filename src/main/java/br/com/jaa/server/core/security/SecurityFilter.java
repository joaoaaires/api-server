package br.com.jaa.server.core.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SecurityFilter extends BasicAuthenticationFilter {
    public SecurityFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
}
