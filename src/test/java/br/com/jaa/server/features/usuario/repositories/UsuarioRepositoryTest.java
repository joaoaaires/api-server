package br.com.jaa.server.features.usuario.repositories;

import br.com.jaa.server.features.usuario.UsuarioAssertions;
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
        UsuarioAssertions.assertionsUsuario(usuarioExpected, usuarioActual);
    }

    @Test
    void readById() {
        Usuario usuarioActual = usuarioRepository.readById(2L);

        Usuario usuarioExpected = UsuarioFixture.getUsuario();

        Assertions.assertNotNull(usuarioActual);
        UsuarioAssertions.assertionsUsuario(usuarioExpected, usuarioActual);
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
        UsuarioAssertions.assertionsUsuario(usuarioExpected, usuarioActual);
    }

    @Test
    void delete() throws Exception {
        Usuario usuario = UsuarioFixture.getUsuario();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraDel = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
        usuario.setDataHoraDel(dataHoraDel);

        Usuario usuarioActual = usuarioRepository.delete(usuario);

        Usuario usuarioExpected = usuarioRepository.readById(usuario.getId());

        Assertions.assertEquals(0L, usuarioActual.getId());
        Assertions.assertNull(usuarioExpected);
    }

}
