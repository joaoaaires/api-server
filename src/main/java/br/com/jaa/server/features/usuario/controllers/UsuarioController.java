package br.com.jaa.server.features.usuario.controllers;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
import br.com.jaa.server.features.usuario.services.UsuarioService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/create")
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> create(
            @RequestBody UsuarioModel usuarioModel
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.create(usuarioModel);
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
