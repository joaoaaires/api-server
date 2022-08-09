package br.com.jaa.server.features.livro.entities;

import br.com.jaa.server.features.shared.entities.Audit;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("livro")
public class Livro extends Audit {

    @Id
    @Column("id")
    private Long id;
    @Column("descricao")
    private String descricao;

    public Livro(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
