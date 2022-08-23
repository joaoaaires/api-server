package br.com.jaa.server.features.usuario.services;

import br.com.jaa.server.core.exceptio.ApiServerException;
import br.com.jaa.server.core.security.SecurityToken;
import br.com.jaa.server.core.security.UsuarioLogged;
import br.com.jaa.server.core.util.ConvertUtil;
import br.com.jaa.server.core.util.ValidationUtil;
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

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private ObjectResponseModelUtil objectResponseModelUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityToken securityToken;

    @Autowired
    private ValidationUtil validationUtil;

    @Autowired
    private UsuarioLogged usuarioLogged;

    public ObjectResponseModel<UsuarioModel> create(UsuarioModel usuarioModel) {
        try {
            ObjectResponseModel<UsuarioModel> responseValidate = validate(usuarioModel.getEmail(), usuarioModel.getPassword());
            if (responseValidate.getStatus() != HttpStatus.OK.value()) {
                return responseValidate;
            }

            boolean existsByEmail = usuarioCrudRepository.existsByEmail(usuarioModel.getEmail());
            if (existsByEmail) {
                throw new ApiServerException(UsuarioServiceMessageEnum.USUARIO_CADASTRADO.name());
            }

            usuarioModel.setId(null);
            usuarioModel.setPassword(passwordEncoder.encode(usuarioModel.getPassword()));
            usuarioModel.setSituacao(1);
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
            Long idLong = ConvertUtil.toLong(id);

            if (idLong <= 0) {
                throw new ApiServerException(UsuarioServiceMessageEnum.USUARIO_ID_NAO_VALIDO.name());
            }

            Optional<Usuario> optionalUsuario = usuarioCrudRepository.findById(idLong);

            if (optionalUsuario.isEmpty()) {
                throw new ApiServerException(UsuarioServiceMessageEnum.USUARIO_NAO_ENCONTRADO.name());
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
            Optional<Usuario> optionalUsuario = usuarioCrudRepository.findById(usuarioModel.getId());
            if (optionalUsuario.isEmpty()) {
                throw new ApiServerException(UsuarioServiceMessageEnum.USUARIO_NAO_ENCONTRADO.name());
            }

            String passwordCrypt = passwordEncoder.encode(usuarioModel.getPassword());
            usuarioModel.setPassword(passwordCrypt);

            Usuario usuario = optionalUsuario.get();
            usuario.setPassword(usuarioModel.getPassword());
            usuario.setSituacao(usuarioModel.getSituacao());
            usuario.setDataHoraSyc(usuarioModel.getDataHoraSyc());
            usuario.setDataHoraAlt(new Timestamp(System.currentTimeMillis()));

            usuario = usuarioCrudRepository.save(usuario);

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

    public ObjectResponseModel<UsuarioModel> read() {
        try {
            Boolean isLogged = usuarioLogged.isLogged();
            if (Boolean.FALSE.equals(isLogged)) {
                throw new ApiServerException(
                        UsuarioServiceMessageEnum.USUARIO_ERROR_NAO_LOGADO.name()
                );
            }

            UsuarioModel usuarioModel = UsuarioModel.fromUsuario(usuarioLogged);
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

    public ObjectResponseModel<UsuarioModel> save(UsuarioModel usuarioModel) {
        if (usuarioModel.getId() != null && usuarioModel.getId() != 0) {
            return update(usuarioModel);
        } else {
            return create(usuarioModel);
        }
    }

    public ObjectResponseModel<UsuarioModel> signIn(
            Map<String, Object> params,
            HttpServletResponse httpResponse
    ) {
        try {
            String email = params.get("email").toString();
            String password = params.get("password").toString();

            ObjectResponseModel responseValidate = validate(email, password);
            if (responseValidate.getStatus() != HttpStatus.OK.value()) {
                return responseValidate;
            }

            Usuario usuario = usuarioCrudRepository.findByEmail(email);
            if (usuario == null) {
                throw new ApiServerException(UsuarioServiceMessageEnum.USUARIO_INFO_INVALIDA.name());
            }

            boolean isPasswordMatch = passwordEncoder.matches(password, usuario.getPassword());
            if (!isPasswordMatch) {
                throw new ApiServerException(UsuarioServiceMessageEnum.USUARIO_INFO_INVALIDA.name());
            }

            securityToken.generateToken(
                    usuario.getId(),
                    usuario.getEmail(),
                    usuario.getPassword(),
                    httpResponse
            );

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

    public ObjectResponseModel<UsuarioModel> signUp(
            Map<String, Object> params
    ) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(null);
        usuarioModel.setEmail(params.get("email").toString());
        usuarioModel.setPassword(params.get("password").toString());
        usuarioModel.setSituacao(1);
        usuarioModel.setDataHoraSyc(new Timestamp(System.currentTimeMillis()));

        return create(usuarioModel);
    }

    private ObjectResponseModel<UsuarioModel> validate(String email, String password) {
        // VALIDAR SE LOGIN FOI INFORMADO
        if (!validationUtil.isNotNullNotEmpty(email)) {
            return objectResponseModelUtil.getObjectResponse(
                    HttpStatus.BAD_REQUEST,
                    UsuarioServiceMessageEnum.USUARIO_ERROR_EMAIL_INVALIDO.name()
            );
        }

        // VALIDAR SE SENHA FOI INFORMADA
        if (!validationUtil.isNotNullNotEmpty(password)) {
            return objectResponseModelUtil.getObjectResponse(
                    HttpStatus.BAD_REQUEST,
                    UsuarioServiceMessageEnum.USUARIO_ERROR_PASSWORD_INVALIDO.name()
            );
        }

        return objectResponseModelUtil.getObjectResponse(HttpStatus.OK, "OK");
    }

}
