//package br.com.jaa.server.core.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Order(1)
//@TestConfiguration
//public class TestSecurityConfig {
//
//    @Bean
//    @Order(1)
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        System.out.println("AQUIIIIIII");
//        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
//        return http.build();
//    }
//
//}
