package br.com.jaa.server.features.cliente;

import br.com.jaa.server.features.cliente.models.ClienteModel;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
import org.junit.jupiter.api.Assertions;

public class ClienteAssertions {

    public static void assertionsClienteModel(ClienteModel clienteExpected, ClienteModel clienteActual) {
        Assertions.assertNotNull(clienteActual.getId());

        Assertions.assertNotNull(clienteActual.getNomeUm());
        Assertions.assertEquals(clienteExpected.getNomeUm(), clienteActual.getNomeUm());

        Assertions.assertNotNull(clienteActual.getNomeDois());
        Assertions.assertEquals(clienteExpected.getNomeDois(), clienteActual.getNomeDois());

        Assertions.assertNotNull(clienteActual.getRegistroUnico());
        Assertions.assertEquals(clienteExpected.getRegistroUnico(), clienteActual.getRegistroUnico());

        Assertions.assertNotNull(clienteActual.getRegistroGeral());
        Assertions.assertEquals(clienteExpected.getRegistroGeral(), clienteActual.getRegistroGeral());

        Assertions.assertNotNull(clienteActual.getDataOrigem());
        Assertions.assertEquals(clienteExpected.getDataOrigem(), clienteActual.getDataOrigem());

        Assertions.assertEquals(clienteExpected.getSite(), clienteActual.getSite());

        Assertions.assertNotNull(clienteActual.getTipo());
        Assertions.assertEquals(clienteExpected.getTipo(), clienteActual.getTipo());

        Assertions.assertNotNull(clienteActual.getSituacao());
        Assertions.assertEquals(clienteExpected.getSituacao(), clienteActual.getSituacao());

        Assertions.assertNull(clienteActual.getDataHoraInc());
        Assertions.assertNull(clienteActual.getDataHoraAlt());
        Assertions.assertNull(clienteActual.getDataHoraDel());
    }
    
}
