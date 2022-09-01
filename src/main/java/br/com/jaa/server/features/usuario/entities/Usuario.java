package br.com.jaa.server.features.usuario.entities;

import br.com.jaa.server.features.shared.entities.Audit;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

@Table("usuario")
public class Usuario extends Audit {

    @Id
    @Column("id")
    private Long id;
    @Column("email")
    private String email;
    @Column("password")
    private String password;
    @Column("situacao")
    private Integer situacao;
    @Column("datahorasyc")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
