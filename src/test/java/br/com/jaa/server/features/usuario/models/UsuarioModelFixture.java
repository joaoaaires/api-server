package br.com.jaa.server.features.usuario.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UsuarioModelFixture {

    public static UsuarioModel getUsuarioModelNew() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(0L);
        usuario.setEmail("joao.aires@gmail.com");
        usuario.setPassword("123");
        usuario.setSituacao(1);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp = new Timestamp(formatter.parse("2022-04-09 12:09:00").getTime());
            usuario.setDataHoraSyc(timestamp);
            usuario.setDataHoraInc(timestamp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return usuario;
    }

    public static UsuarioModel getUsuarioModelOld() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(2L);
        usuario.setEmail("pede.malesuada@aol.ca");
        usuario.setPassword("123321");
        usuario.setSituacao(0);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp = new Timestamp(formatter.parse("2023-02-12 09:29:35").getTime());
            usuario.setDataHoraSyc(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-07-08 17:18:50").getTime());
            usuario.setDataHoraInc(timestamp);

            timestamp = new Timestamp(formatter.parse("2022-08-02 23:30:44").getTime());
            usuario.setDataHoraAlt(timestamp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return usuario;
    }

}
