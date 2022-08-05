package br.com.jaa.server.features.shared.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Column;

import java.sql.Timestamp;

public abstract class Audit {

    @JsonIgnore
    @Column("datahorainc")
    private Timestamp dataHoraInc;
    @JsonIgnore
    @Column("datahoraalt")
    private Timestamp dataHoraAlt;
    @JsonIgnore
    @Column("datahoradel")
    private Timestamp dataHoraDel;

    public Timestamp getDataHoraInc() {
        return dataHoraInc;
    }

    public void setDataHoraInc(Timestamp dataHoraInc) {
        this.dataHoraInc = dataHoraInc;
    }

    public Timestamp getDataHoraAlt() {
        return dataHoraAlt;
    }

    public void setDataHoraAlt(Timestamp dataHoraAlt) {
        this.dataHoraAlt = dataHoraAlt;
    }

    public Timestamp getDataHoraDel() {
        return dataHoraDel;
    }

    public void setDataHoraDel(Timestamp dataHoraDel) {
        this.dataHoraDel = dataHoraDel;
    }
}
