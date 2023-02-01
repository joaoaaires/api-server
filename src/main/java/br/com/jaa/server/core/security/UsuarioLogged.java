package br.com.jaa.server.core.security;

import br.com.jaa.server.features.usuario.entities.Usuario;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UsuarioLogged extends Usuario {

    private Boolean logged;

    public Boolean isLogged() {
        return logged != null && logged;
    }

    public void set(Usuario usuario) {
        this.setId(usuario.getId());
        this.setEmail(usuario.getEmail());
        this.setPassword(usuario.getPassword());
        this.setSituacao(usuario.getSituacao());
        this.setDataHoraSyc(usuario.getDataHoraSyc());
        this.setDataHoraInc(usuario.getDataHoraInc());
        this.setDataHoraAlt(usuario.getDataHoraAlt());
        this.setDataHoraDel(usuario.getDataHoraDel());
        this.logged = true;
    }

}
