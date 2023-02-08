package br.com.jaa.server.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ValidationUtilTest {

    @Autowired
    ValidationUtil validationUti;

    @Test
    void testIsNotNullNotEmpty() {
        boolean value = validationUti.isNotNullNotEmpty("");
        Assertions.assertFalse(value);
        value = validationUti.isNotNullNotEmpty("TRESTE");
        Assertions.assertTrue(value);
    }

    @Test
    void testIsNotEmptyString() {
        boolean value = validationUti.isNotEmpty("");
        Assertions.assertFalse(value);
        value = validationUti.isNotEmpty("TRESTE");
        Assertions.assertTrue(value);
        value = validationUti.isNotEmpty((String) null);
        Assertions.assertFalse(value);
    }

    @Test
    void testIsNotNull() {
        boolean value = validationUti.isNotNull("");
        Assertions.assertTrue(value);
        value = validationUti.isNotNull(null);
        Assertions.assertFalse(value);
    }

    @Test
    void testIsNotEmptyList() {
        boolean value = validationUti.isNotEmpty(Arrays.asList("", ""));
        Assertions.assertTrue(value);
        value = validationUti.isNotEmpty(Arrays.asList());
        Assertions.assertFalse(value);
        value = validationUti.isNotEmpty((List<String>) null);
        Assertions.assertFalse(value);
    }

}
