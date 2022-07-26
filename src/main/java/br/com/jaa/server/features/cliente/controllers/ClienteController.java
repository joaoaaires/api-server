package br.com.jaa.server.features.cliente.controllers;

import br.com.jaa.server.features.cliente.models.ClienteModel;
import br.com.jaa.server.features.cliente.services.ClienteService;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(value = "/create")
    public ResponseEntity<ObjectResponseModel<ClienteModel>> create(
            @RequestBody ClienteModel clienteModel
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.create(clienteModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping(value = "/read/{id}")
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> readById(
            @PathVariable("id") String id
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.readById(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> update(
            @RequestBody UsuarioModel usuarioModel
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.update(usuarioModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> delete(
            @RequestBody UsuarioModel usuarioModel
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.delete(usuarioModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping(value = "")
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> save(
            @RequestBody UsuarioModel usuarioModel
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.save(usuarioModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

}
