package br.com.jaa.server.features.usuario.repositories;

import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.entities.UsuarioFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@SpringBootTest
@Sql(
        value = {"/scripts/usuario_script.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    void create() {
        Usuario usuario = UsuarioFixture.getUsuarioCreate();

        Usuario usuarioActual = usuarioRepository.create(usuario);

        Usuario usuarioExpected = usuarioRepository.readById(usuario.getId());

        Assertions.assertNotNull(usuarioExpected);
        assertionsUsuario(usuarioExpected, usuarioActual);
    }

    @Test
    void readById() {
        Usuario usuarioActual = usuarioRepository.readById(2L);

        Usuario usuarioExpected = UsuarioFixture.getUsuario();

        Assertions.assertNotNull(usuarioActual);
        assertionsUsuario(usuarioExpected, usuarioActual);
    }


    @Test
    void update() throws Exception {
        Usuario usuario = UsuarioFixture.getUsuario();
        usuario.setPassword("123");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraAlt = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
        usuario.setDataHoraAlt(dataHoraAlt);

        Usuario usuarioActual = usuarioRepository.update(usuario);

        Usuario usuarioExpected = usuarioRepository.readById(usuario.getId());

        Assertions.assertNotNull(usuarioExpected);
        assertionsUsuario(usuarioExpected, usuarioActual);
    }

    @Test
    void delete() throws Exception {
        Usuario usuarioExpected = UsuarioFixture.getUsuario();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraDel = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
        usuarioExpected.setDataHoraDel(dataHoraDel);

        Usuario usuario = usuarioRepository.delete(usuarioExpected);

        Assertions.assertEquals(0L, usuario.getId());

        Usuario usuarioActual = usuarioRepository.readById(usuario.getId());

        Assertions.assertNull(usuarioActual);
    }

    private void assertionsUsuario(Usuario usuarioExpected, Usuario usuarioActual) {
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

}
