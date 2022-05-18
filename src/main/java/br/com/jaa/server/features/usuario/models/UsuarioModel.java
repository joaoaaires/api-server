package br.com.jaa.server.features.usuario.models;

import br.com.jaa.server.features.usuario.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioModel extends Usuario {

    /**
     *
     * @param usuario
     * @return id, email, situacao, datahorasync
     */
    public static UsuarioModel fromUsuario(Usuario usuario) {
        UsuarioModel model = new UsuarioModel();
        model.setId(usuario.getId());
        model.setEmail(usuario.getEmail());
        model.setSituacao(usuario.getSituacao());
        model.setDataHoraSyc(usuario.getDataHoraSyc());
        return model;
    }

}
