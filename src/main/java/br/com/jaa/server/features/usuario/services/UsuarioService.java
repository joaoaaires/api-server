package br.com.jaa.server.features.usuario.services;

import br.com.jaa.server.core.exceptio.ApiServerException;
import br.com.jaa.server.core.util.Convert;
import br.com.jaa.server.core.util.ExceptionUtil;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.shared.utils.ObjectResponseModelUtil;
import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.enums.UsuarioServiceMessageEnum;
import br.com.jaa.server.features.usuario.models.UsuarioModel;
import br.com.jaa.server.features.usuario.repositories.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private ObjectResponseModelUtil objectResponseModelUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ObjectResponseModel<UsuarioModel> create(UsuarioModel usuarioModel) {
        try {
            boolean existsByEmail = usuarioCrudRepository.existsByEmail(usuarioModel.getEmail());
            if (existsByEmail) {
                throw new ApiServerException(UsuarioServiceMessageEnum.USUARIO_CADASTRADO.getCode());
            }

            usuarioModel.setId(null);
            usuarioModel.setDataHoraInc(new Timestamp(System.currentTimeMillis()));

            String passwordCrypt = passwordEncoder.encode(usuarioModel.getPassword());
            usuarioModel.setPassword(passwordCrypt);

            Usuario usuario = usuarioCrudRepository.save((Usuario) usuarioModel);
            usuarioModel = UsuarioModel.fromUsuario(usuario);
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

    public ObjectResponseModel<UsuarioModel> readById(String id) {
        try {
            Long idLong = Convert.toLong(id);

            if (idLong <= 0) {
                throw new ApiServerException(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.getCode());
            }

            Optional<Usuario> optionalUsuario = usuarioCrudRepository.findById(idLong);

            if (optionalUsuario.isEmpty()) {
                throw new ApiServerException(UsuarioServiceMessageEnum.USUARIO_NAO_ENCONTRADO.getCode());
            }

            UsuarioModel usuarioModel = UsuarioModel.fromUsuario(optionalUsuario.get());
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
        try {
            boolean existsById = usuarioCrudRepository.existsById(usuarioModel.getId());
            if (!existsById) {
                throw new ApiServerException(UsuarioServiceMessageEnum.USUARIO_NAO_ENCONTRADO.getCode());
            }

            usuarioModel.setDataHoraAlt(new Timestamp(System.currentTimeMillis()));

            String passwordCrypt = passwordEncoder.encode(usuarioModel.getPassword());
            usuarioModel.setPassword(passwordCrypt);

            Usuario usuario = usuarioCrudRepository.save((Usuario) usuarioModel);
            usuarioModel = UsuarioModel.fromUsuario(usuario);
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

    public ObjectResponseModel<UsuarioModel> delete(UsuarioModel usuarioModel) {
        usuarioCrudRepository.delete(usuarioModel);
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
