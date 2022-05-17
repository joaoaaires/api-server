package br.com.jaa.server.features.usuario.controllers;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.fixtures.ObjectResponseFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(
        value = {"/scripts/usuario_script.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class UsuarioControllerTest {

    @Autowired
    UsuarioController usuarioController;

    @Test
    void create() {
        ObjectResponseModel<String> objectResponseExpected = ObjectResponseFixture.getObjectResponse(
                HttpStatus.OK,
                null,
                null
        );

        ResponseEntity<ObjectResponseModel<String>> responseEntityActual = usuarioController.create();
        ObjectResponseModel<String> objectResponseActual = responseEntityActual.getBody();

        assertionsObjectResponseModel(objectResponseExpected, objectResponseActual);
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
