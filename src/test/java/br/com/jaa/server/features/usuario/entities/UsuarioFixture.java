package br.com.jaa.server.features.usuario.entities;

import org.junit.jupiter.api.Assertions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UsuarioFixture {

    public static Usuario getUsuarioNew() {
        Usuario usuario = new Usuario();
        usuario.setId(null);
        usuario.setEmail("joao.aires@gmail.com");
        usuario.setPassword("123");
        usuario.setSituacao(0);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Timestamp timestamp = new Timestamp(formatter.parse("2022-05-18 16:33:01").getTime());
            usuario.setDataHoraSyc(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-05-18 16:34:02").getTime());
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

    public static Usuario getUsuarioOld() {
        Usuario usuario = new Usuario();
        usuario.setId(2L);
        usuario.setEmail("pede.malesuada@aol.ca");
        usuario.setPassword("123321");
        usuario.setSituacao(0);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp = new Timestamp(formatter.parse("2022-05-18 16:42:01").getTime());
            usuario.setDataHoraSyc(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-05-18 16:43:02").getTime());
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
