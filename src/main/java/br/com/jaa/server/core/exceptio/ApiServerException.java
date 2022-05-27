package br.com.jaa.server.core.exceptio;

public class ApiServerException extends Exception {

    public ApiServerException(String message) {
        super(message);
    }

    public ApiServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
