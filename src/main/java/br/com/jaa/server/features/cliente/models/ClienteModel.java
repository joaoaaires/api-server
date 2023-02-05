package br.com.jaa.server.features.cliente.models;

import br.com.jaa.server.features.cliente.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteModel extends Cliente {

    public static ClienteModel fromCliente(Cliente cliente) {
        ClienteModel model = new ClienteModel();
        model.setId(cliente.getId());
        model.setNomeUm(cliente.getNomeUm());
        model.setNomeDois(cliente.getNomeDois());
        model.setRegistroUnico(cliente.getRegistroUnico());
        model.setRegistroGeral(cliente.getRegistroGeral());
        model.setDataOrigem(cliente.getDataOrigem());
        model.setSite(cliente.getSite());
        model.setTipo(cliente.getTipo());
        model.setSituacao(cliente.getSituacao());
        return model;
    }

}
