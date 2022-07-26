package br.com.jaa.server.features.cliente.models;

import br.com.jaa.server.features.cliente.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteModel extends Cliente {
}
