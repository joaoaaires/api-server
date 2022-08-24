package br.com.jaa.server.features.usuario.services;

import br.com.jaa.server.core.security.UsuarioLogged;
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
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Sql(
        value = {"/scripts/usuario_script.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioLogged usuarioLogged;

    @Test
    void create() {
        UsuarioModel usuarioModelExpected = UsuarioModelFixture.getUsuarioModelNew();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.create(usuarioModelExpected);

        assertionsObjectResponseModel(objectResponseActual);

        UsuarioModel usuarioModelActual = objectResponseActual.getData();

        UsuarioAssertions.assertionsUsuarioModel(usuarioModelExpected, usuarioModelActual);
    }

    @Test
    void createErrorEmail() {
        UsuarioModel usuarioModelExpected = UsuarioModelFixture.getUsuarioModelNew();
        usuarioModelExpected.setEmail(null);

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.create(usuarioModelExpected);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ERROR_EMAIL_INVALIDO.name(), objectResponseActual.getMessage());
    }

    @Test
    void createErrorPassword() {
        UsuarioModel usuarioModelExpected = UsuarioModelFixture.getUsuarioModelNew();
        usuarioModelExpected.setPassword(null);

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.create(usuarioModelExpected);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ERROR_PASSWORD_INVALIDO.name(), objectResponseActual.getMessage());
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
    void read() {
        usuarioLogged.set(UsuarioModelFixture.getUsuarioModelOld());

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.read();

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void readErrorLogged() {
        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.read();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ERROR_NAO_LOGADO.name(), objectResponseActual.getMessage());
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

    @Test
    void signIn() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();

        Map<String, Object> params = new HashMap<>();
        params.put("email", usuarioExpected.getEmail());
        params.put("password", usuarioExpected.getPassword());

        MockHttpServletResponse response = new MockHttpServletResponse();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.signIn(params, response);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void signInErrorEmail() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();

        Map<String, Object> params = new HashMap<>();
        params.put("password", usuarioExpected.getPassword());

        MockHttpServletResponse response = new MockHttpServletResponse();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.signIn(params, response);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ERROR_EMAIL_INVALIDO.name(), objectResponseActual.getMessage());
    }

    @Test
    void signInErrorPassword() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();

        Map<String, Object> params = new HashMap<>();
        params.put("email", usuarioExpected.getEmail());

        MockHttpServletResponse response = new MockHttpServletResponse();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.signIn(params, response);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ERROR_PASSWORD_INVALIDO.name(), objectResponseActual.getMessage());
    }

    @Test
    void signInErrorEmailNotFound() {
        Map<String, Object> params = new HashMap<>();
        params.put("email", "joao@gmail.com");
        params.put("password", "321");

        MockHttpServletResponse response = new MockHttpServletResponse();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.signIn(params, response);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_INFO_INVALIDA.name(), objectResponseActual.getMessage());
    }

    @Test
    void signInErrorPasswordNotFound() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();

        Map<String, Object> params = new HashMap<>();
        params.put("email", usuarioExpected.getEmail());
        params.put("password", "321");

        MockHttpServletResponse response = new MockHttpServletResponse();

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.signIn(params, response);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_INFO_INVALIDA.name(), objectResponseActual.getMessage());
    }

    @Test
    void signUp() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelNew();

        Map<String, Object> params = new HashMap<>();
        params.put("email", usuarioExpected.getEmail());
        params.put("password", usuarioExpected.getPassword());

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.signUp(params);

        assertionsObjectResponseModel(objectResponseActual);

        assertionsObjectResponseModel(objectResponseActual);
    }

    @Test
    void signUpErrorEmail() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();

        Map<String, Object> params = new HashMap<>();
        params.put("password", usuarioExpected.getPassword());

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.signUp(params);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ERROR_EMAIL_INVALIDO.name(), objectResponseActual.getMessage());
    }

    @Test
    void signUpErrorPassword() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();

        Map<String, Object> params = new HashMap<>();
        params.put("email", usuarioExpected.getEmail());

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.signUp(params);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ERROR_PASSWORD_INVALIDO.name(), objectResponseActual.getMessage());
    }

    @Test
    void signUpErrorEmailDuplicado() {
        UsuarioModel usuarioExpected = UsuarioModelFixture.getUsuarioModelOld();

        Map<String, Object> params = new HashMap<>();
        params.put("email", usuarioExpected.getEmail());
        params.put("password", usuarioExpected.getPassword());

        ObjectResponseModel<UsuarioModel> objectResponseActual = usuarioService.signUp(params);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_CADASTRADO.name(), objectResponseActual.getMessage());
    }

    private void assertionsObjectResponseModel(ObjectResponseModel<UsuarioModel> objectResponseActual) {
        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getData());
        Assertions.assertNull(objectResponseActual.getMessage());
    }

}
