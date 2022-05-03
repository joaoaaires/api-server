package br.com.jaa.server.core.exceptio;

public class ApiServerRuntimeException extends RuntimeException {

    public ApiServerRuntimeException(String message) {
        super(message);
    }
}
