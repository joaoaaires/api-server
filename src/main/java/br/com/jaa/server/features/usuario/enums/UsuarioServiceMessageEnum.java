package br.com.jaa.server.features.usuario.enums;

public enum UsuarioServiceMessageEnum {
    USUARIO_ID_NAO_VALIDO("USUARIO_ID_NAO_VALIDO");


    private final String code;


    UsuarioServiceMessageEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
