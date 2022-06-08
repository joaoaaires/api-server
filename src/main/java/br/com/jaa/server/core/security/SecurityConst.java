package br.com.jaa.server.core.security;

public class SecurityConst {

    private SecurityConst() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Dias para expirar o token no front
     */
    public static final int DAYS_EXPIRATION = 10;
    /**
     * Dias limit para renovacao de token
     */
    public static final int DAYS_MINI_TOKEN = 3;

    public static final String KEY_AUTH_RESPONSE = "Authorization-Response";

}
