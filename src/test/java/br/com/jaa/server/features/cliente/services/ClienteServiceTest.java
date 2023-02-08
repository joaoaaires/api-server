package br.com.jaa.server.features.cliente.services;

import br.com.jaa.server.features.cliente.ClienteAssertions;
import br.com.jaa.server.features.cliente.enums.ClienteServiceMessageEnum;
import br.com.jaa.server.features.cliente.models.ClienteModel;
import br.com.jaa.server.features.cliente.models.ClienteModelFixture;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(
        value = {"/scripts/cliente_script.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class ClienteServiceTest {

    @Autowired
    ClienteService clienteService;

    @Test
    void create() {
        ClienteModel clienteModelExpected = ClienteModelFixture.getClienteModelNew();

        ObjectResponseModel<ClienteModel> objectResponseActual = clienteService.create(clienteModelExpected);

        assertionsObjectResponseModel(objectResponseActual);

        ClienteModel clienteModelActual = objectResponseActual.getData();

        ClienteAssertions.assertionsClienteModel(clienteModelExpected, clienteModelActual);
    }

    @Test
    void createExistsByRegistroUnico() {
        ClienteModel clienteModelExpected = ClienteModelFixture.getClienteModelOld();

        ObjectResponseModel<ClienteModel> objectResponseActual = clienteService.create(clienteModelExpected);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(ClienteServiceMessageEnum.CLIENTE_CADASTRADO.name(), objectResponseActual.getMessage());
    }

    @Test
    void readById() {
        ClienteModel clienteModelExpected = ClienteModelFixture.getClienteModelOld();

        ObjectResponseModel<ClienteModel> objectResponseActual = clienteService.readById(
                String.valueOf(clienteModelExpected.getId())
        );

        assertionsObjectResponseModel(objectResponseActual);

        ClienteModel clienteModelActual = objectResponseActual.getData();

        ClienteAssertions.assertionsClienteModel(clienteModelExpected, clienteModelActual);
    }

    @Test
    void readByIdErrorInvalido() {

        ObjectResponseModel<ClienteModel> objectResponseActual = clienteService.readById(
                String.valueOf(-1)
        );

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(ClienteServiceMessageEnum.CLIENTE_ID_INVALIDO.name(), objectResponseActual.getMessage());
    }

    @Test
    void readByIdErrorNotFound() {
        ObjectResponseModel<ClienteModel> objectResponseActual = clienteService.readById(
                String.valueOf(9999L)
        );

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(ClienteServiceMessageEnum.CLIENTE_NAO_ENCONTRADO.name(), objectResponseActual.getMessage());
    }

    @Test
    void update() {
        ClienteModel clienteModelExpected = ClienteModelFixture.getClienteModelOld();

        ObjectResponseModel<ClienteModel> objectResponseActual = clienteService.update(clienteModelExpected);

        assertionsObjectResponseModel(objectResponseActual);

        ClienteModel clienteModelActual = objectResponseActual.getData();

        ClienteAssertions.assertionsClienteModel(clienteModelExpected, clienteModelActual);
    }

    @Test
    void updateIdNull() {
        ClienteModel clienteModelExpected = ClienteModelFixture.getClienteModelOld();
        clienteModelExpected.setId(null);

        ObjectResponseModel<ClienteModel> objectResponseActual = clienteService.update(clienteModelExpected);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(ClienteServiceMessageEnum.CLIENTE_ID_INVALIDO.name(), objectResponseActual.getMessage());
    }

    @Test
    void updateIdZero() {
        ClienteModel clienteModelExpected = ClienteModelFixture.getClienteModelOld();
        clienteModelExpected.setId(0L);

        ObjectResponseModel<ClienteModel> objectResponseActual = clienteService.update(clienteModelExpected);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(ClienteServiceMessageEnum.CLIENTE_ID_INVALIDO.name(), objectResponseActual.getMessage());
    }

    @Test
    void updateNotFound() {
        ClienteModel clienteModelExpected = ClienteModelFixture.getClienteModelOld();
        clienteModelExpected.setId(9999L);

        ObjectResponseModel<ClienteModel> objectResponseActual = clienteService.update(clienteModelExpected);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNull(objectResponseActual.getData());
        Assertions.assertNotNull(objectResponseActual.getMessage());
        Assertions.assertEquals(ClienteServiceMessageEnum.CLIENTE_NAO_ENCONTRADO.name(), objectResponseActual.getMessage());
    }

    @Test
    void delete() {
        ClienteModel clienteModel = ClienteModelFixture.getClienteModelOld();

        ObjectResponseModel<ClienteModel> objectResponseActual = clienteService.delete(clienteModel);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getData());
        Assertions.assertNull(objectResponseActual.getMessage());
    }

    @Test
    void save() {
        ClienteModel clienteModel = ClienteModelFixture.getClienteModelOld();

        ObjectResponseModel<ClienteModel> objectResponseActual = clienteService.save(clienteModel);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getData());
        Assertions.assertNull(objectResponseActual.getMessage());
    }

    private void assertionsObjectResponseModel(ObjectResponseModel<ClienteModel> objectResponseActual) {
        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getData());
        Assertions.assertNull(objectResponseActual.getMessage());
    }

}
