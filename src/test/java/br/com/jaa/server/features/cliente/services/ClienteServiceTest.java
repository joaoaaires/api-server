package br.com.jaa.server.features.cliente.services;

import br.com.jaa.server.features.cliente.ClienteAssertions;
import br.com.jaa.server.features.cliente.models.ClienteModel;
import br.com.jaa.server.features.cliente.models.ClienteModelFixture;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.usuario.UsuarioAssertions;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
import br.com.jaa.server.features.usuario.models.UsuarioModelFixture;
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

    private void assertionsObjectResponseModel(ObjectResponseModel<ClienteModel> objectResponseActual) {
        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseActual.getStatus());
        Assertions.assertNotNull(objectResponseActual.getData());
        Assertions.assertNull(objectResponseActual.getMessage());
    }

}
