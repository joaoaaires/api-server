package br.com.jaa.server.features.usuario.repositories;

import br.com.jaa.server.features.shared.repositories.DefaultRepository;
import br.com.jaa.server.features.usuario.entities.Usuario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository extends DefaultRepository {

    private static final String QUERY_CREATE = "INSERT INTO usuario SET\n" +
            "email = :EMAIL,\n" +
            "situacao = :SITUACAO,\n" +
            "password = :PASSWORD,\n" +
            "datahorasyc = :DATAHORASYC,\n" +
            "datahorainc = :DATAHORAINC,\n" +
            "datahoraalt = :DATAHORAALT,\n" +
            "datahoradel = :DATAHORADEL;";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS usuario (\n" +
            "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "email VARCHAR(255) NOT NULL UNIQUE,\n" +
            "password VARCHAR(255) NOT NULL,\n" +
            "situacao INT NOT NULL,\n" +
            "datahorasyc DATETIME NOT NULL,\n" +
            "datahorainc DATETIME NOT NULL,\n" +
            "datahoraalt DATETIME,\n" +
            "datahoradel DATETIME\n" +
            ");";

    public Usuario create(Usuario usuario) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = getMapSqlParameterSource(usuario);
        this.defaultNamedParameterJdbcTemplate.update(QUERY_CREATE, params, keyHolder);
        Number numberId = keyHolder.getKey();
        if (numberId != null) {
            long id = numberId.longValue();
            usuario.setId(id);
        }
        return usuario;
    }

    public void createTable() {
        this.defaultJdbcTemplate.execute(CREATE_TABLE);
    }

    private MapSqlParameterSource getMapSqlParameterSource(Usuario usuario) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", usuario.getId());
        params.addValue("email", usuario.getEmail());
        params.addValue("password", usuario.getPassword());
        params.addValue("situacao", usuario.getSituacao());
        params.addValue("datahorasyc", usuario.getDataHoraSyc());
        params.addValue("datahorainc", usuario.getDataHoraInc());
        params.addValue("datahoraalt", usuario.getDataHoraAlt());
        params.addValue("datahoradel", usuario.getDataHoraDel());
        return params;
    }

}

