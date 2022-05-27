package br.com.jaa.server.features.usuario.repositories;

import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.entities.UsuarioFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

@SpringBootTest
public class UsuarioRepositoryMockTest {

    @MockBean
    protected JdbcTemplate defaultJdbcTemplate;

    @MockBean
    protected NamedParameterJdbcTemplate defaultNamedParameterJdbcTemplate;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    void create() {
        Mockito.doReturn(1).when(defaultNamedParameterJdbcTemplate).update(
                Mockito.anyString(),
                Mockito.any(MapSqlParameterSource.class),
                Mockito.any(KeyHolder.class)
        );

        Usuario usuario = usuarioRepository.create(UsuarioFixture.getUsuarioCreate());

        Assertions.assertEquals(0L, usuario.getId());
    }

}
