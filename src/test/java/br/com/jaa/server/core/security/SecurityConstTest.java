package br.com.jaa.server.core.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class SecurityConstTest {

    @Test
    void securityConst() throws Exception {
        Constructor<SecurityConst> constructor = SecurityConst.class.getDeclaredConstructor();
        Assertions.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);

        Exception exception = Assertions.assertThrows(
                InvocationTargetException.class,
                () -> constructor.newInstance()
        );
        Assertions.assertNull(exception.getMessage());
    }

}
