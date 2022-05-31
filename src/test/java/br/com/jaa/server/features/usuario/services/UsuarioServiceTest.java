package br.com.jaa.server.features.usuario.services;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.usuario.enums.UsuarioServiceMessageEnum;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
import br.com.jaa.server.features.usuario.models.UsuarioModelFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@SpringBootTest
@Sql(
        value = {"/scripts/usuario_script.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @Test
    void create() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelCreate();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.create(usuarioExpected);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void readByIdOk() {
       UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModel();

        String id = String.valueOf(usuarioExpected.getId());
        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.readById(id);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void readByIdErrorZero() {
        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.readById("0");

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.getCode(), objectResponseActual.getMessage());
        Assertions.assertNull(objectResponseActual.getData());
    }

    @Test
    void readByIdErrorEmpyt() {
        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.readById("");

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.getCode(), objectResponseActual.getMessage());
        Assertions.assertNull(objectResponseActual.getData());
    }

    @Test
    void readByIdErrorNull() {
        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.readById(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.getCode(), objectResponseActual.getMessage());
        Assertions.assertNull(objectResponseActual.getData());
    }

    @Test
    void readByIdErrorNegative() {
        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.readById("-1");

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.getCode(), objectResponseActual.getMessage());
        Assertions.assertNull(objectResponseActual.getData());
    }

    @Test
    void update() throws Exception {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModel();
        usuarioExpected.setPassword("123321");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraAlt = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
        usuarioExpected.setDataHoraAlt(dataHoraAlt);

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.update(usuarioExpected);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void delete() throws Exception {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModel();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraAlt = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
        usuarioExpected.setDataHoraDel(dataHoraAlt);

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.delete(usuarioExpected);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void saveCreateZero() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelCreate();
        usuarioExpected.setId(0L);

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.save(usuarioExpected);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void saveCreateNull() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelCreate();
        usuarioExpected.setId(null);

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.save(usuarioExpected);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void saveUpdate() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModel();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.save(usuarioExpected);

        assertionsObjectResponseModel(objectResponseActual);
    }

    private void assertionsObjectResponseModel(ObjectResponseModel<UsuarioModel> objectResponseActual) {
        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getData());
        Assertions.assertNull(objectResponseActual.getMessage());
    }

}
