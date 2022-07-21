package br.com.jaa.server.features.usuario;

import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
import org.junit.jupiter.api.Assertions;

public class UsuarioAssertions {

    public static void assertionsUsuario(Usuario usuarioExpected, Usuario usuarioActual) {
        Assertions.assertNotNull(usuarioActual.getId());
        Assertions.assertEquals(usuarioExpected.getId(), usuarioActual.getId());

        Assertions.assertNotNull(usuarioActual.getEmail());
        Assertions.assertEquals(usuarioExpected.getEmail(), usuarioActual.getEmail());

        Assertions.assertNotNull(usuarioActual.getPassword());
        Assertions.assertEquals(usuarioExpected.getPassword(), usuarioActual.getPassword());

        Assertions.assertNotNull(usuarioActual.getSituacao());
        Assertions.assertEquals(usuarioExpected.getSituacao(), usuarioActual.getSituacao());

        Assertions.assertNotNull(usuarioActual.getDataHoraSyc());
        Assertions.assertEquals(usuarioExpected.getDataHoraSyc(), usuarioActual.getDataHoraSyc());

        Assertions.assertNotNull(usuarioActual.getDataHoraInc());
        Assertions.assertEquals(usuarioExpected.getDataHoraInc(), usuarioActual.getDataHoraInc());

        Assertions.assertNotNull(usuarioActual.getDataHoraAlt());
        Assertions.assertEquals(usuarioExpected.getDataHoraAlt(), usuarioActual.getDataHoraAlt());

        Assertions.assertNotNull(usuarioActual.getDataHoraDel());
        Assertions.assertEquals(usuarioExpected.getDataHoraDel(), usuarioActual.getDataHoraDel());
    }

    public static void assertionsUsuarioModel(UsuarioModel usuarioExpected, UsuarioModel usuarioActual) {
        Assertions.assertNotNull(usuarioActual.getId());

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
