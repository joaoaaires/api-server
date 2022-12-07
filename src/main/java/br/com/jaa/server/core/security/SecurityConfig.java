package br.com.jaa.server.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(
                        csrf -> csrf.disable()
                )
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/javainuse-openapi/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/usuario/signup", "/usuario/signin").permitAll()
                                .requestMatchers(HttpMethod.GET, "/").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .addFilter(securityFilter())
                )
                .httpBasic(Customizer.withDefaults())
                .build();
//        http
//                .cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/javainuse-openapi/**").permitAll()
//                .antMatchers("/graphql", "/graphql/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/usuario/signup", "/usuario/signin").permitAll()
//                .antMatchers(HttpMethod.GET, "/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> {
//                    auth
//
//
//
//                            .anyRequest().authenticated();
//                })
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .build();
    }

    //    @Bean
//    public SecurityToken securityToken() {
//        return new SecurityToken();
//    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public SecurityFilter securityFilter() {
        return new SecurityFilter(authenticationManager());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}



