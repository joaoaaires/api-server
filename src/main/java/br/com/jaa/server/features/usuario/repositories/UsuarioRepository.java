package br.com.jaa.server.features.usuario.repositories;

import br.com.jaa.server.features.shared.repositories.DefaultRepository;
import br.com.jaa.server.features.usuario.entities.Usuario;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository extends DefaultRepository {

    private static final String QUERY_CREATE = "INSERT INTO usuario SET\n" +
            "email = :EMAIL,\n" +
            "password = :PASSWORD,\n" +
            "situacao = :SITUACAO,\n" +
            "datahorasyc = :DATAHORASYC,\n" +
            "datahorainc = :DATAHORAINC,\n" +
            "datahoraalt = :DATAHORAALT,\n" +
            "datahoradel = :DATAHORADEL;";

    private static final String QUERY_READ = "SELECT u.id,\n" +
            "u.email,\n" +
            "u.password,\n" +
            "u.situacao,\n" +
            "u.datahorasyc,\n" +
            "u.datahorainc,\n" +
            "u.datahoraalt,\n" +
            "u.datahoradel\n" +
            "FROM usuario u\n" +
            "WHERE u.id = :ID;";

    private static final String QUERY_UPDATE = "UPDATE usuario SET\n" +
            "email = :EMAIL,\n" +
            "password = :PASSWORD,\n" +
            "situacao = :SITUACAO,\n" +
            "datahorasyc = :DATAHORASYC,\n" +
            "datahorainc = :DATAHORAINC,\n" +
            "datahoraalt = :DATAHORAALT,\n" +
            "datahoradel = :DATAHORADEL\n" +
            "WHERE id = :ID;";

    private static final String QUERY_DELETE = "DELETE FROM usuario\n" +
            "WHERE id = :ID;";

    public Usuario create(Usuario usuario) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = getMapSqlParameterSource(usuario);
        this.defaultNamedParameterJdbcTemplate.update(QUERY_CREATE, params, keyHolder);
        Number numberId = keyHolder.getKey();
        usuario.setId(numberId != null ? numberId.longValue() : 0L);
        return usuario;
    }

    public Usuario readById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("ID", id);
        List<Usuario> list = this.defaultNamedParameterJdbcTemplate.query(QUERY_READ, params, rowMapper);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public Usuario update(Usuario usuario) {
        MapSqlParameterSource params = getMapSqlParameterSource(usuario);
        this.defaultNamedParameterJdbcTemplate.update(QUERY_UPDATE, params);
        return usuario;
    }

    public Usuario delete(Usuario usuario) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("ID", usuario.getId());
        this.defaultNamedParameterJdbcTemplate.update(QUERY_DELETE, params);
        usuario.setId(0L);
        return usuario;
    }

    private RowMapper<Usuario> rowMapper = (rs, rowNum) -> {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setEmail(rs.getString("email"));
        usuario.setPassword(rs.getString("password"));
        usuario.setSituacao(rs.getInt("situacao"));
        usuario.setDataHoraSyc(rs.getTimestamp("datahorasyc"));
        usuario.setDataHoraInc(rs.getTimestamp("datahorainc"));
        usuario.setDataHoraAlt(rs.getTimestamp("datahoraalt"));
        usuario.setDataHoraDel(rs.getTimestamp("datahoradel"));
        return usuario;
    };

    private MapSqlParameterSource getMapSqlParameterSource(Usuario usuario) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", usuario.getId());
        params.addValue("EMAIL", usuario.getEmail());
        params.addValue("PASSWORD", usuario.getPassword());
        params.addValue("SITUACAO", usuario.getSituacao());
        params.addValue("DATAHORASYC", usuario.getDataHoraSyc());
        params.addValue("DATAHORAINC", usuario.getDataHoraInc());
        params.addValue("DATAHORAALT", usuario.getDataHoraAlt());
        params.addValue("DATAHORADEL", usuario.getDataHoraDel());
        return params;
    }

}

