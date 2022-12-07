package br.com.jaa.server.core.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

@SpringBootTest
public class SecurityTokenTest {

    @Autowired
    SecurityToken securityToken;

    @Test
    void generateTokenLongIdLong() {
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        securityToken.generateToken(1L, "joao.aires@sovis.com.br", "123", httpResponse);
        Assertions.assertNotNull(httpResponse.getHeader(SecurityConst.KEY_AUTH_RESPONSE));
    }

    @Test
    void generateTokenLongIdString() {
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        securityToken.generateToken("1", "joao.aires@sovis.com.br", "123", httpResponse);
        Assertions.assertNotNull(httpResponse.getHeader(SecurityConst.KEY_AUTH_RESPONSE));
    }

}
