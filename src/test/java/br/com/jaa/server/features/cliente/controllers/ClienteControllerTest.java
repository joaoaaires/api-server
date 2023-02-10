package br.com.jaa.server.features.cliente.controllers;

import br.com.jaa.server.core.security.UsuarioLogged;
import br.com.jaa.server.features.cliente.models.ClienteModel;
import br.com.jaa.server.features.cliente.models.ClienteModelFixture;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.Map;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Sql(
        value = {"/scripts/cliente_script.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class ClienteControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private UsuarioLogged usuarioLogged;

    private String url;

    @BeforeEach
    void setUp() {
        url = "http://localhost:" + port + "/cliente";
    }

    @Test
    void create() {
        ClienteModel clienteModel = ClienteModelFixture.getClienteModelNew();

        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.postForEntity(
                url.concat("/create"),
                clienteModel,
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();

        Assertions.assertEquals(8, data.size());
        Assertions.assertEquals(6, data.get("id"));
        Assertions.assertEquals(clienteModel.getNomeUm(), data.get("nomeum"));
        Assertions.assertEquals(clienteModel.getNomeDois(), data.get("nomedois"));
    }

    @Test
    void readById() {
        ClienteModel clienteModel = ClienteModelFixture.getClienteModelOld();

        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.getForEntity(
                url.concat("/read/").concat(String.valueOf(clienteModel.getId())),
                ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());

        Map<String, Object> data = (Map<String, Object>) objectResponseModel.getData();

        Assertions.assertEquals(9, data.size());
        Assertions.assertEquals(clienteModel.getId().intValue(), data.get("id"));
        Assertions.assertEquals(clienteModel.getNomeUm(), data.get("nomeum"));
        Assertions.assertEquals(clienteModel.getNomeDois(), data.get("nomedois"));
    }

}
