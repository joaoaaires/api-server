package br.com.jaa.server.features.cliente.entities;

import br.com.jaa.server.features.shared.entities.Audit;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Table("cliente")
public class Cliente extends Audit {

    @Id
    @Column("id")
    private Long id;
    @Column("nomeum")
    private String nomeUm;
    @Column("nomedois")
    private String nomeDois;
    @Column("dataorigem")
    private Date dataOrigem;
    @Column("site")
    private String site;
    @Column("tipo")
    private String tipo;
    @Column("situacao")
    private Integer situacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUm() {
        return nomeUm;
    }

    public void setNomeUm(String nomeUm) {
        this.nomeUm = nomeUm;
    }

    public String getNomeDois() {
        return nomeDois;
    }

    public void setNomeDois(String nomeDois) {
        this.nomeDois = nomeDois;
    }

    public Date getDataOrigem() {
        return dataOrigem;
    }

    public void setDataOrigem(Date dataOrigem) {
        this.dataOrigem = dataOrigem;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }
}
