package br.com.jaa.server.features.shared.entities;

import java.sql.Timestamp;

public abstract class Audit {

    private Timestamp dataHoraInc;
    private Timestamp dataHoraAlt;
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
