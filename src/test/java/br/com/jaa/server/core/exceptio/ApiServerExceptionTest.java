package br.com.jaa.server.core.exceptio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ApiServerExceptionTest {

    @Test
    void message() {
        String message = "teste";
        try {
            throw  new ApiServerException(message);
        } catch (ApiServerException exception) {
            Assertions.assertEquals(message, exception.getMessage());
        }
    }

    @Test
    void messageCause() {
        String message = "teste";
        SQLException throwables = new SQLException();
        try {
            throw  new ApiServerException(message, throwables);
        } catch (ApiServerException exception) {
            Assertions.assertEquals(message, exception.getMessage());
            Assertions.assertEquals(throwables, exception.getCause());
        }
    }

}
