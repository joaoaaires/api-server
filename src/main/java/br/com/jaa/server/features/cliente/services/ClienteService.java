package br.com.jaa.server.features.cliente.services;

import br.com.jaa.server.core.exceptio.ApiServerException;
import br.com.jaa.server.features.cliente.entities.Cliente;
import br.com.jaa.server.features.cliente.enums.ClienteServiceMessageEnum;
import br.com.jaa.server.features.cliente.models.ClienteModel;
import br.com.jaa.server.features.cliente.repositories.ClienteCrudRepository;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.shared.utils.ObjectResponseModelUtil;
import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.enums.UsuarioServiceMessageEnum;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
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
        try {
            boolean existsByRegistroUnico = clienteCrudRepository.existsByRegistroUnico(clienteModel.getRegistroUnico());
            if (existsByRegistroUnico) {
                throw new ApiServerException(ClienteServiceMessageEnum.CLIENTE_CADASTRADO.name());
            }

            clienteModel.setId(null);

            Cliente cliente = clienteCrudRepository.save((Cliente) clienteModel);
            clienteModel = ClienteModel.fromCliente(cliente);
            return objectResponseModelUtil.getObjectResponse(
                    HttpStatus.OK,
                    clienteModel
            );
        } catch (ApiServerException exception) {
            return objectResponseModelUtil.getObjectResponse(
                    HttpStatus.BAD_REQUEST,
                    exception.getMessage()
            );
        }
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
