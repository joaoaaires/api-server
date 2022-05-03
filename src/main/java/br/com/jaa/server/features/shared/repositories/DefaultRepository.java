package br.com.jaa.server.features.shared.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class DefaultRepository {

    @Autowired
    protected JdbcTemplate defaultJdbcTemplate;

    @Autowired
    protected NamedParameterJdbcTemplate defaultNamedParameterJdbcTemplate;

}
