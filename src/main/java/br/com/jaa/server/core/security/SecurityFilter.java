package br.com.jaa.server.core.security;

import br.com.jaa.server.core.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

@Component
public class SecurityFilter extends BasicAuthenticationFilter {

    @Autowired
    private ValidationUtil validationUtil;

    public SecurityFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse,
            FilterChain chain
    ) throws IOException, ServletException {
        String token = httpRequest.getHeader("Authorization");
        if (validationUtil.isNotNullNotEmpty(token)) {
            List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            "admin",
                            null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(httpRequest, httpResponse);
    }

}
