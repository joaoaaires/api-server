package br.com.jaa.server.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        SecurityRoute routeGetPermitAll = SecurityRoute.GET;
//        SecurityRoute routePostPermitAll = SecurityRoute.POST;
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/javainuse-openapi/**").permitAll()
//                .antMatchers(routeGetPermitAll.httpMethod, routeGetPermitAll.routes).permitAll()
//                .antMatchers(routePostPermitAll.httpMethod, routePostPermitAll.routes).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(securityFilter())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public SecurityFilter securityFilter() throws Exception {
        return new SecurityFilter(authenticationManager());
    }

    @Bean
    public SecurityToken securityToken() {
        return new SecurityToken();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
