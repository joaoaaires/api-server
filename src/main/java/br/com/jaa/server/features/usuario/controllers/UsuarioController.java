package br.com.jaa.server.features.usuario.controllers;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @PostMapping(value = "")
    public ResponseEntity<ObjectResponseModel<String>> create() {
        ObjectResponseModel<String> responseModel = new ObjectResponseModel<>(HttpStatus.OK);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

}
