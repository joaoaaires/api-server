package br.com.jaa.server.features.usuario.controllers;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
import br.com.jaa.server.features.usuario.services.UsuarioService;
import io.swagger.v3.core.model.ApiDescription;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/create")
    @SecurityRequirement(name = "authorization")
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> create(
            @RequestBody UsuarioModel usuarioModel
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.create(usuarioModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping(value = "/read/{id}")
    @SecurityRequirement(name = "authorization")
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> readById(
            @PathVariable("id") String id
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.readById(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping(value = "")
    @SecurityRequirement(name = "authorization")
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> read() {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.read();
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping(value = "/update")
    @SecurityRequirement(name = "authorization")
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> update(
            @RequestBody UsuarioModel usuarioModel
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.update(usuarioModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping(value = "/delete")
    @SecurityRequirement(name = "authorization")
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> delete(
            @RequestBody UsuarioModel usuarioModel
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.delete(usuarioModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping(value = "")
    @SecurityRequirement(name = "authorization")
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> save(
            @RequestBody UsuarioModel usuarioModel
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.save(usuarioModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping(value = "/signin")
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(
                                    example = "{ \"email\":\"string\", \"password\":\"string\" }"
                            )
                    )
            )
    )
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> signIn(
            @RequestBody Map<String, Object> params,
            HttpServletResponse httpResponse
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.signIn(params, httpResponse);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping(value = "/signup")
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(
                                    example = "{ \"email\":\"string\", \"password\":\"string\" }"
                            )
                    )
            )
    )
    public ResponseEntity<ObjectResponseModel<UsuarioModel>> signUp(
            @RequestBody Map<String, Object> params
    ) {
        ObjectResponseModel<UsuarioModel> responseModel = usuarioService.signUp(params);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

}
