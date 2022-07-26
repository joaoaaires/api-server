package br.com.jaa.server.features.cliente.entities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ClienteFixture {

    public static Cliente getClienteNew() {
        Cliente usuario = new Cliente();
        usuario.setId(null);
        usuario.setNomeUm("joao.aires@gmail.com");
        usuario.setNomeDois("joao.aires@gmail.com");
        usuario.setDataOrigem("123");
        usuario.setSite("123");
        usuario.setTipo("123");
        usuario.setSituacao(0);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Timestamp timestamp = new Timestamp(formatter.parse("2022-05-18 16:33:01").getTime());
            usuario.setDataHoraInc(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-05-18 16:35:03").getTime());
            usuario.setDataHoraAlt(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-05-18 16:36:04").getTime());
            usuario.setDataHoraDel(timestamp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return usuario;
    }

    public static Cliente getClienteOld() {
        Cliente usuario = new Cliente();
        usuario.setId(2L);
        usuario.setEmail("pede.malesuada@aol.ca");
        usuario.setPassword("123321");
        usuario.setSituacao(0);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Timestamp timestamp = new Timestamp(formatter.parse("2022-05-18 16:42:01").getTime());
            usuario.setDataHoraInc(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-05-18 16:44:03").getTime());
            usuario.setDataHoraAlt(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-05-18 16:45:04").getTime());
            usuario.setDataHoraDel(timestamp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return usuario;
    }
    
}
