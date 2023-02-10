package br.com.jaa.server.features.cliente.services;

import br.com.jaa.server.core.exceptio.ApiServerException;
import br.com.jaa.server.core.util.ConvertUtil;
import br.com.jaa.server.features.cliente.entities.Cliente;
import br.com.jaa.server.features.cliente.enums.ClienteServiceMessageEnum;
import br.com.jaa.server.features.cliente.models.ClienteModel;
import br.com.jaa.server.features.cliente.repositories.ClienteCrudRepository;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.shared.utils.ObjectResponseModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

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

            clienteModel.setDataHoraInc(new Timestamp(System.currentTimeMillis()));

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
        try {
            Long idLong = ConvertUtil.toLong(id);

            if (idLong <= 0) {
                throw new ApiServerException(ClienteServiceMessageEnum.CLIENTE_ID_INVALIDO.name());
            }

            Optional<Cliente> optionalCliente = clienteCrudRepository.findById(idLong);

            if (optionalCliente.isEmpty()) {
                throw new ApiServerException(ClienteServiceMessageEnum.CLIENTE_NAO_ENCONTRADO.name());
            }

            ClienteModel clienteModel = ClienteModel.fromCliente(optionalCliente.get());
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

    public ObjectResponseModel<ClienteModel> update(ClienteModel clienteModel) {
        try {
            if (clienteModel.getId() == null || clienteModel.getId() <= 0) {
                throw new ApiServerException(ClienteServiceMessageEnum.CLIENTE_ID_INVALIDO.name());
            }

            Optional<Cliente> optionalCliente = clienteCrudRepository.findById(clienteModel.getId());
            if (optionalCliente.isEmpty()) {
                throw new ApiServerException(ClienteServiceMessageEnum.CLIENTE_NAO_ENCONTRADO.name());
            }

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

    public ObjectResponseModel<ClienteModel> delete(ClienteModel clienteModel) {

        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.BAD_REQUEST,
                clienteModel
        );
    }

    public ObjectResponseModel<ClienteModel> save(ClienteModel clienteModel) {

        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.BAD_REQUEST,
                clienteModel
        );
    }

}
