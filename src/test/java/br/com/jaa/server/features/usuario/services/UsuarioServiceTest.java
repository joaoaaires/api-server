package br.com.jaa.server.features.usuario.services;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.usuario.UsuarioAssertions;
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
        UsuarioModel usuarioModelExpected = UsuarioModelFixture.getUsuarioModelNew();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.create(usuarioModelExpected);

        assertionsObjectResponseModel(objectResponseActual);

        UsuarioModel usuarioModelActual = objectResponseActual.getData();

        UsuarioAssertions.assertionsUsuarioModel(usuarioModelExpected, usuarioModelActual);
    }

    @Test
    void createErrorEmailDuplicado() {
        UsuarioModel usuarioModelExpected = UsuarioModelFixture.getUsuarioModelOld();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.create(usuarioModelExpected);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_CADASTRADO.name(), objectResponseActual.getMessage());
    }

    @Test
    void readById() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.readById(
                String.valueOf(usuarioExpected.getId())
        );

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void readByIdZeroNotFound() {
        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.readById("0");

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.name(), objectResponseActual.getMessage());
        Assertions.assertNull(objectResponseActual.getData());
    }

    @Test
    void readByIdEmptyNotFound() {
        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.readById("");

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.name(), objectResponseActual.getMessage());
        Assertions.assertNull(objectResponseActual.getData());
    }

    @Test
    void readByIdNullNotFound() {
        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.readById(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.name(), objectResponseActual.getMessage());
        Assertions.assertNull(objectResponseActual.getData());
    }

    @Test
    void readByIdNotFound() {
        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.readById("6516516515");

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_NAO_ENCONTRADO.name(), objectResponseActual.getMessage());
        Assertions.assertNull(objectResponseActual.getData());
    }

    @Test
    void readByIdErrorNegative() {
        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.readById("-1");

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.name(), objectResponseActual.getMessage());
        Assertions.assertNull(objectResponseActual.getData());
    }

    @Test
    void update() throws Exception {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();
        usuarioExpected.setPassword("123321");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraAlt = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
        usuarioExpected.setDataHoraAlt(dataHoraAlt);

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.update(usuarioExpected);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void updateIdError() throws Exception {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();
        usuarioExpected.setId(-1L);
        usuarioExpected.setPassword("123321");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraAlt = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
        usuarioExpected.setDataHoraAlt(dataHoraAlt);

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.update(usuarioExpected);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_NAO_ENCONTRADO.name(), objectResponseActual.getMessage());
        Assertions.assertNull(objectResponseActual.getData());
    }

    @Test
    void delete() throws Exception {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraAlt = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
        usuarioExpected.setDataHoraDel(dataHoraAlt);

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.delete(usuarioExpected);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void saveCreateZero() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelNew();
        usuarioExpected.setId(0L);

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.save(usuarioExpected);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void saveCreateNull() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelNew();
        usuarioExpected.setId(null);

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.save(usuarioExpected);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void saveUpdate() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.save(usuarioExpected);

        assertionsObjectResponseModel(objectResponseActual);
    }

    private void assertionsObjectResponseModel(ObjectResponseModel<UsuarioModel> objectResponseActual) {
        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getData());
        Assertions.assertNull(objectResponseActual.getMessage());
    }

}
