package br.com.jaa.server.features.usuario.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@ActiveProfiles("test")
class UsuarioModelTest {

    @Test
    void testUsuarioModel() throws Exception {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(1L);
        usuario.setEmail("joao.aires@gmail.com");
        usuario.setPassword("123");
        usuario.setSituacao(1);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraSyc = new Timestamp(formatter.parse("2022-04-09 12:09:00").getTime());
        usuario.setDataHoraSyc(dataHoraSyc);

        Assertions.assertEquals(1L, usuario.getId());
        Assertions.assertEquals("joao.aires@gmail.com", usuario.getEmail());
        Assertions.assertEquals("123", usuario.getPassword());
        Assertions.assertEquals(1, usuario.getSituacao());
        Assertions.assertEquals(dataHoraSyc, usuario.getDataHoraSyc());
    }

}
