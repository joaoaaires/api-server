package br.com.jaa.server.features.usuario.repositories;

import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.fixtures.UsuarioFixture;
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
        Usuario usuario = UsuarioFixture.getUsuarioNovo();
        Usuario response = usuarioRepository.create(usuario);

        Assertions.assertEquals(usuario.getId(), response.getId());
        Assertions.assertEquals(usuario.getEmail(), response.getEmail());
        Assertions.assertEquals(usuario.getPassword(), response.getPassword());
    }

    @Test
    void readById() {
        Usuario usuario = UsuarioFixture.getUsuarioAntigo();
        Usuario response = usuarioRepository.readById(usuario.getId());

        Assertions.assertEquals(usuario.getId(), response.getId());
        Assertions.assertEquals(usuario.getEmail(), response.getEmail());
    }


    @Test
    void update() throws Exception {
        Usuario usuario = UsuarioFixture.getUsuarioAntigo();

        usuario.setPassword("123321");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraAlt = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
        usuario.setDataHoraAlt(dataHoraAlt);

        Usuario response = usuarioRepository.update(usuario);

        Assertions.assertEquals(usuario.getId(), response.getId());
        Assertions.assertEquals(usuario.getPassword(), response.getPassword());
        Assertions.assertEquals(usuario.getDataHoraAlt(), response.getDataHoraAlt());
    }

    @Test
    void delete() throws Exception {
        Usuario usuario = UsuarioFixture.getUsuarioAntigo();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraDel = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
        usuario.setDataHoraDel(dataHoraDel);

        Usuario responseDelete = usuarioRepository.delete(usuario);
        Assertions.assertEquals(0L, responseDelete.getId());

        Usuario responseIsNull = usuarioRepository.readById(usuario.getId());
        Assertions.assertNull(responseIsNull);
    }

}
