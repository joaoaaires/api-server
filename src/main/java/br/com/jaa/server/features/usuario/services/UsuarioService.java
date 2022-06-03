package br.com.jaa.server.features.usuario.services;

import br.com.jaa.server.core.exceptio.ApiServerException;
import br.com.jaa.server.core.util.Convert;
import br.com.jaa.server.core.util.ExceptionUtil;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.shared.utils.ObjectResponseModelUtil;
import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.enums.UsuarioServiceMessageEnum;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
import br.com.jaa.server.features.usuario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectResponseModelUtil objectResponseModelUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ExceptionUtil exceptionUtil;

    public ObjectResponseModel<UsuarioModel> create(UsuarioModel usuarioModel) {
        String passwordCrypt = passwordEncoder.encode(usuarioModel.getPassword());
        usuarioModel.setPassword(passwordCrypt);

        Usuario usuario = usuarioRepository.create(usuarioModel);
        usuarioModel = UsuarioModel.fromUsuario(usuario);
        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.OK,
                usuarioModel
        );
    }

    public ObjectResponseModel<UsuarioModel> readById(String id) {
        try {
            Long idLong = Convert.toLong(id);

            if (idLong <= 0) {
                throw new ApiServerException(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.getCode());
            }

            Usuario usuario = usuarioRepository.readById(idLong);
            UsuarioModel usuarioModel = UsuarioModel.fromUsuario(usuario);
            return objectResponseModelUtil.getObjectResponse(
                    HttpStatus.OK,
                    usuarioModel
            );
        } catch (ApiServerException exception) {
            return objectResponseModelUtil.getObjectResponse(
                    HttpStatus.BAD_REQUEST,
                    exception.getMessage()
            );
        }
    }

    public ObjectResponseModel<UsuarioModel> update(UsuarioModel usuarioModel) {
        Usuario usuario = usuarioRepository.update(usuarioModel);
        usuarioModel = UsuarioModel.fromUsuario(usuario);
        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.OK,
                usuarioModel
        );
    }

    public ObjectResponseModel<UsuarioModel> delete(UsuarioModel usuarioModel) {
        Usuario usuario = usuarioRepository.delete(usuarioModel);
        usuarioModel = UsuarioModel.fromUsuario(usuario);
        return objectResponseModelUtil.getObjectResponse(
                HttpStatus.OK,
                usuarioModel
        );
    }

    public ObjectResponseModel<UsuarioModel> save(UsuarioModel usuarioModel) {
        if (usuarioModel.getId() != null && usuarioModel.getId() != 0) {
            return update(usuarioModel);
        } else {
            return create(usuarioModel);
        }
    }

}
