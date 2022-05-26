package br.com.jaa.server.features.usuario.controllers;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

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
    void create() {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModelCreate();

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
        Assertions.assertEquals(usuarioModel.getSituacao(), data.get("situacao"));
        Assertions.assertEquals("2022-04-09T12:09:00.000+00:00", data.get("datahorasyc"));
    }

    @Test
    void readById() {
        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.getForEntity(
                url.concat("/read/" + 1L),
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();

        System.out.println(data);

        Assertions.assertEquals(4, data.size());
        Assertions.assertEquals(1, data.get("id"));
        Assertions.assertEquals("lobortis.augue@aol.edu", data.get("email"));
        Assertions.assertEquals(0, data.get("situacao"));
        Assertions.assertEquals("2022-05-18T16:42:01.000+00:00", data.get("datahorasyc"));
    }


    @Test
    void update() throws Exception {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModel();
        usuarioModel.setSituacao(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraSyc = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
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
        Assertions.assertEquals("2022-05-02T12:09:00.000+00:00", data.get("datahorasyc"));
    }

    @Test
    void delete() throws Exception {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModel();
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

        Assertions.assertEquals(4, data.size());
        Assertions.assertEquals(0, data.get("id"));
        Assertions.assertEquals(usuarioModel.getEmail(), data.get("email"));
        Assertions.assertEquals(usuarioModel.getSituacao(), data.get("situacao"));
        Assertions.assertEquals("2023-02-12T09:29:35.000+00:00", data.get("datahorasyc"));
    }

    @Test
    void saveCreate() {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModelCreate();

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
        Assertions.assertEquals(usuarioModel.getSituacao(), data.get("situacao"));
        Assertions.assertEquals("2022-04-09T12:09:00.000+00:00", data.get("datahorasyc"));
    }

    @Test
    void saveUpdate() throws Exception {
        UsuarioModel usuarioModel = UsuarioModelFixture.getUsuarioModel();
        usuarioModel.setSituacao(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dataHoraSyc = new Timestamp(formatter.parse("2022-05-02 12:09:00").getTime());
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
        Assertions.assertEquals("2022-05-02T12:09:00.000+00:00", data.get("datahorasyc"));
    }

}
