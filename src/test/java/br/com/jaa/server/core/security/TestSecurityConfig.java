//package br.com.jaa.server.core.security;
//
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Order(1)
//@TestConfiguration
//public class TestSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Disable CSRF
//        http.csrf().disable()
//                // Permit all requests without authentication
//                .authorizeRequests().anyRequest().permitAll();
//    }
//
//}
