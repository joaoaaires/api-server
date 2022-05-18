package br.com.jaa.server.features.usuario.controllers;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
import br.com.jaa.server.features.usuario.entities.UsuarioFixture;
import br.com.jaa.server.features.usuario.models.UsuarioModelFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Sql(
        value = {"/scripts/usuario_script.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class UsuarioControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String url;

    @BeforeEach
    void setUp() {
        url = "http://localhost:" + port + "/usuario";
    }

    @Test
    void saveCreate() {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModelCreate();
        usuarioModel.setId(0L);

        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.postForEntity(
                url,
                usuarioModel,
                ObjectResponseModel.class
        );
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();
        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());

        //Assertions.assertNotNull(objectResponseModel.getData());

//        System.out.println(responseEntity);
//
//        Assertions.assertNotNull(responseEntity);

//        UsuarioModel usuarioModelActual = objectResponseModel.getData();
//        Assertions.assertEquals(usuarioModel.getId(), usuarioModelActual.getId());
//        Assertions.assertEquals(usuarioModel.getEmail(), usuarioModelActual.getEmail());
//        Assertions.assertEquals(usuarioModel.getPassword(), usuarioModelActual.getPassword());
    }

    @Test
    void readById() {
//        Usuario usuario = UsuarioFixture.getUsuarioAntigo();
//        ObjectResponseModel<Usuario> objectResponseExpected = ObjectResponseFixture.getObjectResponse(
//                HttpStatus.OK,
//                usuario
//        );
//
//        ObjectResponseModel<Usuario> objectResponseActual = usuarioService.readById(usuario.getId());
//        assertionsObjectResponseModel(objectResponseExpected, objectResponseActual);
//
//        Usuario usuarioActual = objectResponseActual.getData();
//        assertionsUsuario(usuario, usuarioActual);
    }


    @Test
    void update() throws Exception {
//        Usuario usuario = UsuarioFixture.getUsuarioAntigo();
//
//        usuario.setPassword("123321");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Timestamp dataHoraAlt = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
//        usuario.setDataHoraAlt(dataHoraAlt);
//
//        ObjectResponseModel<Usuario> objectResponseExpected = ObjectResponseFixture.getObjectResponse(
//                HttpStatus.OK,
//                usuario
//        );
//
//        ObjectResponseModel<Usuario> objectResponseActual = usuarioService.update(usuario);
//        assertionsObjectResponseModel(objectResponseExpected, objectResponseActual);
//
//        Usuario usuarioActual = objectResponseActual.getData();
//        assertionsUsuario(usuario, usuarioActual);
    }

    @Test
    void delete() throws Exception {
//        Usuario usuario = UsuarioFixture.getUsuarioAntigo();
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Timestamp dataHoraAlt = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
//        usuario.setDataHoraDel(dataHoraAlt);
//
//        ObjectResponseModel<Usuario> objectResponseExpected = ObjectResponseFixture.getObjectResponse(
//                HttpStatus.OK,
//                usuario
//        );
//
//        ObjectResponseModel<Usuario> objectResponseActual = usuarioService.delete(usuario);
//        assertionsObjectResponseModel(objectResponseExpected, objectResponseActual);
//
//        Usuario usuarioActual = objectResponseActual.getData();
//        assertionsUsuario(usuario, usuarioActual);
    }

    private void assertionsObjectResponseModel(ObjectResponseModel<String> objectResponseExpected, ObjectResponseModel<String> objectResponseActual) {
        Assertions.assertEquals(objectResponseExpected.getStatus(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseExpected.getData());
        Assertions.assertNull(objectResponseExpected.getMessage());
    }

    private void assertionsUsuario(Usuario usuario, Usuario usuarioActual) {
        Assertions.assertEquals(usuario.getId(), usuarioActual.getId());
        Assertions.assertEquals(usuario.getEmail(), usuarioActual.getEmail());
        Assertions.assertEquals(usuario.getPassword(), usuarioActual.getPassword());
    }

}
