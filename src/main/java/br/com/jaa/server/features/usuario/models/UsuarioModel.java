package br.com.jaa.server.features.usuario.models;

import br.com.jaa.server.features.usuario.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioModel extends Usuario {

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public Integer getSituacao() {
        return super.getSituacao();
    }

    @Override
    public void setSituacao(Integer situacao) {
        super.setSituacao(situacao);
    }

    @Override
    public Timestamp getDataHoraSyc() {
        return super.getDataHoraSyc();
    }

    @Override
    public void setDataHoraSyc(Timestamp dataHoraSyc) {
        super.setDataHoraSyc(dataHoraSyc);
    }

    public static UsuarioModel fromUsuario(Usuario usuario) {
        UsuarioModel model = new UsuarioModel();
        model.setId(usuario.getId());
        model.setEmail(usuario.getEmail());
        model.setSituacao(usuario.getSituacao());
        model.setDataHoraSyc(usuario.getDataHoraSyc());
        return model;
    }

}
