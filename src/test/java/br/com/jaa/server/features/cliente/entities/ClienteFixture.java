package br.com.jaa.server.features.cliente.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ClienteFixture {

    public static Cliente getClienteNew() {

        Cliente cliente = new Cliente();
        cliente.setId(null);
        cliente.setNomeUm("João Antonio");
        cliente.setNomeDois("Aires");
        cliente.setRegistroUnico("39704031971");
        cliente.setRegistroGeral("114754561");

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(formatter.parse("1993-11-06").getTime());
            cliente.setDataOrigem(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        cliente.setSite(null);
        cliente.setTipo("F");
        cliente.setSituacao(0);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Timestamp timestamp = new Timestamp(formatter.parse("2022-05-18 16:33:01").getTime());
            cliente.setDataHoraInc(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-05-18 16:35:03").getTime());
            cliente.setDataHoraAlt(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-05-18 16:36:04").getTime());
            cliente.setDataHoraDel(timestamp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cliente;
    }

    public static Cliente getClienteOld() {
        Cliente cliente = new Cliente();
        cliente.setId(2L);
        cliente.setNomeUm("Vitória e Gael Contábil Ltda");
        cliente.setNomeDois("Vitória e Gael Contábil Ltda");
        cliente.setRegistroUnico("58904448000110");
        cliente.setRegistroGeral("1112568375");

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(formatter.parse("2017-01-23").getTime());
            cliente.setDataOrigem(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        cliente.setSite("www.teste.com.br");
        cliente.setTipo("F");
        cliente.setSituacao(0);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Timestamp timestamp = new Timestamp(formatter.parse("2022-05-18 16:33:01").getTime());
            cliente.setDataHoraInc(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-05-18 16:35:03").getTime());
            cliente.setDataHoraAlt(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-05-18 16:36:04").getTime());
            cliente.setDataHoraDel(timestamp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cliente;
    }

}
