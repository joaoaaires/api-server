package br.com.jaa.server.features.usuario.services;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.shared.utils.ObjectResponseModelUtil;
import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectResponseModelUtil objectResponseModelUtil;

    public ObjectResponseModel<Usuario> create(Usuario params) {
        Usuario response = usuarioRepository.create(params);
        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.OK,
                response
        );
    }

    public ObjectResponseModel<Usuario> readById(Long id) {
        Usuario response = usuarioRepository.readById(id);
        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.OK,
                response
        );
    }

    public ObjectResponseModel<Usuario> update(Usuario usuario) {
        Usuario response = usuarioRepository.update(usuario);
        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.OK,
                response
        );
    }

    public ObjectResponseModel<Usuario> delete(Usuario usuario) {
        Usuario response = usuarioRepository.delete(usuario);
        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.OK,
                response
        );
    }

}
