package br.com.jaa.server.features.cliente.services;

import br.com.jaa.server.features.cliente.models.ClienteModel;
import br.com.jaa.server.features.cliente.repositories.ClienteCrudRepository;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.shared.utils.ObjectResponseModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ObjectResponseModelUtil objectResponseModelUtil;

    public ObjectResponseModel<ClienteModel> create(ClienteModel clienteModel) {

        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.BAD_REQUEST,
                null
        );
    }

    public ObjectResponseModel<ClienteModel> readById(String id) {

        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.BAD_REQUEST,
                null
        );
    }

    public ObjectResponseModel<ClienteModel> update(ClienteModel clienteModel) {

        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.BAD_REQUEST,
                null
        );
    }

    public ObjectResponseModel<ClienteModel> delete(ClienteModel clienteModel) {

        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.BAD_REQUEST,
                null
        );
    }

    public ObjectResponseModel<ClienteModel> save(ClienteModel clienteModel) {

        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.BAD_REQUEST,
                null
        );
    }

}
