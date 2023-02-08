package br.com.jaa.server.features.usuario.models;

import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.entities.UsuarioFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

class UsuarioModelTest {

    @Test
    void constructor() throws Exception {
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

    @Test
    void fromUsuario() throws Exception {
        Usuario usuarioExpected = UsuarioFixture.getUsuarioOld();
        UsuarioModel usuarioActual = UsuarioModel.fromUsuario(usuarioExpected);

        Assertions.assertNotNull(usuarioActual.getId());
        Assertions.assertEquals(usuarioExpected.getId(), usuarioActual.getId());

        Assertions.assertNotNull(usuarioActual.getEmail());
        Assertions.assertEquals(usuarioExpected.getEmail(), usuarioActual.getEmail());

        Assertions.assertNull(usuarioActual.getPassword());

        Assertions.assertNotNull(usuarioActual.getSituacao());
        Assertions.assertEquals(usuarioExpected.getSituacao(), usuarioActual.getSituacao());

        Assertions.assertNotNull(usuarioActual.getDataHoraSyc());
        Assertions.assertEquals(usuarioExpected.getDataHoraSyc(), usuarioActual.getDataHoraSyc());

        Assertions.assertNull(usuarioActual.getDataHoraInc());
        Assertions.assertNull(usuarioActual.getDataHoraAlt());
        Assertions.assertNull(usuarioActual.getDataHoraDel());
    }

}
