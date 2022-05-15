package br.com.jaa.server.core.util;

import br.com.jaa.server.core.exceptio.ApiServerRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExceptionUtilTest {

    @Autowired
    ExceptionUtil exceptionUtil;

    @Test
    void testGetRuntimeException() {
        Assertions.assertThrowsExactly(
                ApiServerRuntimeException.class,
                () -> exceptionUtil.getRuntimeException("Test"),
                "Test"
        );
    }

}
