package br.com.jaa.server.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConvertTest {

    @Test
    void toLongOk() {
        Long valueLong = 152L;
        String valueString = "152";

        Long valueLongResult = ConvertUtil.toLong(valueString);

        Assertions.assertEquals(valueLong, valueLongResult);
    }

    @Test
    void toLongNull() {
        Long valueLong = 0L;
        String valueString = null;

        Long valueLongResult = ConvertUtil.toLong(valueString);

        Assertions.assertEquals(valueLong, valueLongResult);
    }

    @Test
    void toLongAlfa() {
        Long valueLong = 0L;
        String valueString = "152A";

        Long valueLongResult = ConvertUtil.toLong(valueString);

        Assertions.assertEquals(valueLong, valueLongResult);
    }

    @Test
    void toLongEmpty() {
        Long valueLong = 0L;
        String valueString = "";

        Long valueLongResult = ConvertUtil.toLong(valueString);

        Assertions.assertEquals(valueLong, valueLongResult);
    }

}
