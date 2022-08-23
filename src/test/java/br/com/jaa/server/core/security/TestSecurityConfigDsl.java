package br.com.jaa.server.core.security;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Component;

@Component
public class TestSecurityConfigDsl extends AbstractHttpConfigurer<TestSecurityConfigDsl, HttpSecurity> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
    }

}
