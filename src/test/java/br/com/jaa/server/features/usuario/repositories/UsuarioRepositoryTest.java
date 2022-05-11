package br.com.jaa.server.features.usuario.repositories;

import br.com.jaa.server.features.usuario.entities.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@SpringBootTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioRepository.createTable();

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("joao.aires@gmail.com");
        usuario.setPassword("123");
        usuario.setSituacao(1);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp dataHoraSyc = new Timestamp(formatter.parse("2022-04-09 12:09:00").getTime());
            usuario.setDataHoraSyc(dataHoraSyc);
            usuario.setDataHoraInc(dataHoraSyc);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void testA() {
        Usuario response = usuarioRepository.create(this.usuario);

        Assertions.assertEquals(usuario.getId(), response.getId());
        Assertions.assertEquals(usuario.getEmail(), response.getEmail());
    }

    @Test
    void testB() {
        Long id = 1L;
        Usuario response = usuarioRepository.readById(id);

        Assertions.assertEquals(usuario.getId(), response.getId());
        Assertions.assertEquals(usuario.getEmail(), response.getEmail());
    }


    @Test
    void testC() throws Exception {
        Long id = 1L;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraAlt = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());

        usuario.setPassword("987654");
        usuario.setDataHoraAlt(dataHoraAlt);

        usuarioRepository.update(usuario);

        Usuario response = usuarioRepository.readById(id);

        Assertions.assertEquals(usuario.getId(), response.getId());
        Assertions.assertEquals(usuario.getPassword(), response.getPassword());
        Assertions.assertEquals(usuario.getDataHoraAlt(), response.getDataHoraAlt());
    }

    @Test
    void testD() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraDel = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());

        usuario.setDataHoraDel(dataHoraDel);

        Usuario responseDelete = usuarioRepository.delete(usuario);
        Usuario responseIsNull = usuarioRepository.readById(usuario.getId());

        Assertions.assertEquals(0L, responseDelete.getId());
        Assertions.assertNull(responseIsNull);
    }

}
