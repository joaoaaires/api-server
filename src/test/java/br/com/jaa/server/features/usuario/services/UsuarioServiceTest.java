package br.com.jaa.server.features.usuario.services;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.usuario.entities.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@SpringBootTest
@ActiveProfiles("test")
class UsuarioServiceTest {

    @Mock
    UsuarioService usuarioService;

    Usuario usuario;

    @BeforeEach
    void setUp() {
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
        ObjectResponseModel<Usuario> response = new ObjectResponseModel<>(HttpStatus.OK);
        response.setData(usuario);

        Mockito.when(usuarioService.create(Mockito.any())).then(invocationOnMock -> response);

        ObjectResponseModel<Usuario> responseModel = usuarioService.create(usuario);

        Assertions.assertEquals(response.getStatus(), responseModel.getStatus());
        Assertions.assertEquals(response.getData(), responseModel.getData());
        Assertions.assertNull(responseModel.getMessage());
    }

    @Test
    void testB() {
    }


    @Test
    void testC() throws Exception {
    }

    @Test
    void testD() throws Exception {

    }

}
