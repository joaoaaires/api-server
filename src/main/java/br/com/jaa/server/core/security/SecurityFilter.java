//package br.com.jaa.server.core.security;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class SecurityFilter extends BasicAuthenticationFilter {
//    public SecurityFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest httpRequest,
//            HttpServletResponse httpResponse,
//            FilterChain chain
//    ) throws IOException, ServletException {
//        chain.doFilter(httpRequest, httpResponse);
//    }
//
//}
