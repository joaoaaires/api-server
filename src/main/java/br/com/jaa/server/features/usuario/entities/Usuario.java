package br.com.jaa.server.features.usuario.entities;

import br.com.jaa.server.features.shared.entities.Audit;

import java.sql.Timestamp;

public class Usuario extends Audit {

    private Long id;
    private String email;
    private String password;
    private Integer situacao;
    private Timestamp dataHoraSyc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Timestamp getDataHoraSyc() {
        return dataHoraSyc;
    }

    public void setDataHoraSyc(Timestamp dataHoraSyc) {
        this.dataHoraSyc = dataHoraSyc;
    }

}
