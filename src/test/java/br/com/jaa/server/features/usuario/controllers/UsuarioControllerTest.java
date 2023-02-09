package br.com.jaa.server.features.usuario.controllers;

import br.com.jaa.server.core.security.UsuarioLogged;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.usuario.enums.UsuarioServiceMessageEnum;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
import br.com.jaa.server.features.usuario.models.UsuarioModelFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

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

    @MockBean
    private UsuarioLogged usuarioLogged;

    private String url;

    @BeforeEach
    void setUp() {
        url = "http://localhost:" + port + "/usuario";
    }

    @Test
    void create() {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModelNew();

        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.postForEntity(
                url.concat("/create"),
                usuarioModel,
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();

        Assertions.assertEquals(4, data.size());
        Assertions.assertEquals(6, data.get("id"));
        Assertions.assertEquals(usuarioModel.getEmail(), data.get("email"));
        Assertions.assertEquals(1, usuarioModel.getSituacao());
    }

    @Test
    void readById() {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModelOld();
        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.getForEntity(
                url.concat("/read/").concat(String.valueOf(usuarioModel.getId())),
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();

        Assertions.assertEquals(4, data.size());
        Assertions.assertEquals(2, data.get("id"));
        Assertions.assertEquals(usuarioModel.getEmail(), data.get("email"));
        Assertions.assertEquals(usuarioModel.getSituacao(), data.get("situacao"));
    }

    @Test
    void readByIdErrorId() {
        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.getForEntity(
                url.concat("/read/ERROR"),
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getMessage());
        Assertions.assertEquals(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.name(), objectResponseModel.getMessage());
        Assertions.assertNull(objectResponseModel.getData());
    }

    @Test
    void read() {
        Mockito.when(usuarioLogged.isLogged()).thenReturn(true);

        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModelOld();
        Mockito.when(usuarioLogged.getId()).thenReturn(usuarioModel.getId());
        Mockito.when(usuarioLogged.getEmail()).thenReturn(usuarioModel.getEmail());
        Mockito.when(usuarioLogged.getSituacao()).thenReturn(usuarioModel.getSituacao());
        Mockito.when(usuarioLogged.getDataHoraSyc()).thenReturn(usuarioModel.getDataHoraSyc());

        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.getForEntity(
                url.concat(""),
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();

        Assertions.assertEquals(4, data.size());
        Assertions.assertEquals(usuarioModel.getId().intValue(), data.get("id"));
        Assertions.assertEquals(usuarioModel.getEmail(), data.get("email"));
        Assertions.assertEquals(usuarioModel.getSituacao(), data.get("situacao"));
    }


    @Test
    void update() throws Exception {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModelOld();
        usuarioModel.setSituacao(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        Timestamp dataHoraSyc = new Timestamp(formatter.parse("2022-05-02 15:09:00").getTime());
        usuarioModel.setDataHoraSyc(dataHoraSyc);

        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.postForEntity(
                url.concat("/update"),
                usuarioModel,
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();

        Assertions.assertEquals(4, data.size());
        Assertions.assertEquals(2, data.get("id"));
        Assertions.assertEquals(usuarioModel.getEmail(), data.get("email"));
        Assertions.assertEquals(usuarioModel.getSituacao(), data.get("situacao"));
    }

    @Test
    void delete() throws Exception {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModelOld();
        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.postForEntity(
                url.concat("/delete"),
                usuarioModel,
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();
    }

    @Test
    void saveCreate() {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModelNew();

        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.postForEntity(
                url,
                usuarioModel,
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();

        Assertions.assertEquals(4, data.size());
        Assertions.assertEquals(6, data.get("id"));
        Assertions.assertEquals(usuarioModel.getEmail(), data.get("email"));
        Assertions.assertEquals(1, usuarioModel.getSituacao());
    }

    @Test
    void saveUpdate() throws Exception {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModelOld();
        usuarioModel.setSituacao(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        Timestamp dataHoraSyc = new Timestamp(formatter.parse("2022-05-02 15:09:00").getTime());
        usuarioModel.setDataHoraSyc(dataHoraSyc);

        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.postForEntity(
                url,
                usuarioModel,
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();

        Assertions.assertEquals(4, data.size());
        Assertions.assertEquals(2, data.get("id"));
        Assertions.assertEquals(usuarioModel.getEmail(), data.get("email"));
        Assertions.assertEquals(usuarioModel.getSituacao(), data.get("situacao"));
    }

    @Test
    void signIn() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("email", "lobortis.augue@aol.edu");
        params.put("password", "123321");

        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.postForEntity(
                url.concat("/signin"),
                params,
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();

        Assertions.assertEquals(4, data.size());
        Assertions.assertEquals(1, data.get("id"));
        Assertions.assertEquals("lobortis.augue@aol.edu", data.get("email"));
    }

    @Test
    void signUp() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("email", "joao.aires@sovis.com.br");
        params.put("password", "123321");

        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.postForEntity(
                url.concat("/signup"),
                params,
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();

        Assertions.assertEquals(4, data.size());
        Assertions.assertEquals(6, data.get("id"));
        Assertions.assertEquals("joao.aires@sovis.com.br", data.get("email"));
    }

}
