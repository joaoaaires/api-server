package br.com.jaa.server.features.cliente.controllers;

import br.com.jaa.server.features.cliente.models.ClienteModel;
import br.com.jaa.server.features.cliente.services.ClienteService;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente")
@SecurityRequirement(name = "authorization")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(value = "/create")
    public ResponseEntity<ObjectResponseModel<ClienteModel>> create(
            @RequestBody ClienteModel clienteModel
    ) {
        ObjectResponseModel<ClienteModel> responseModel = clienteService.create(clienteModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping(value = "/read/{id}")
    public ResponseEntity<ObjectResponseModel<ClienteModel>> readById(
            @PathVariable("id") String id
    ) {
        ObjectResponseModel<ClienteModel> responseModel = clienteService.readById(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ObjectResponseModel<ClienteModel>> update(
            @RequestBody ClienteModel clienteModel
    ) {
        ObjectResponseModel<ClienteModel> responseModel = clienteService.update(clienteModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<ObjectResponseModel<ClienteModel>> delete(
            @RequestBody ClienteModel clienteModel
    ) {
        ObjectResponseModel<ClienteModel> responseModel = clienteService.delete(clienteModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping(value = "")
    public ResponseEntity<ObjectResponseModel<ClienteModel>> save(
            @RequestBody ClienteModel clienteModel
    ) {
        ObjectResponseModel<ClienteModel> responseModel = clienteService.save(clienteModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

}
