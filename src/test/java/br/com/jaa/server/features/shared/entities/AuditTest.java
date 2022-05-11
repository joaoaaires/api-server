package br.com.jaa.server.features.shared.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

class AuditTest {

    @Test
    void createAudit() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Timestamp dataHoraInc = new Timestamp(formatter.parse("2022-04-09 12:09:00").getTime());
        Timestamp dataHoraAlt = new Timestamp(formatter.parse("2022-04-09 13:09:00").getTime());
        Timestamp dataHoraDel = new Timestamp(formatter.parse("2022-04-09 14:09:00").getTime());

        Carro carro = new Carro();
        carro.setDataHoraInc(dataHoraInc);
        carro.setDataHoraAlt(dataHoraAlt);
        carro.setDataHoraDel(dataHoraDel);

        Assertions.assertEquals(dataHoraInc, carro.getDataHoraInc());
        Assertions.assertEquals(dataHoraAlt, carro.getDataHoraAlt());
        Assertions.assertEquals(dataHoraDel, carro.getDataHoraDel());
    }

    public class Carro extends Audit {}

}
