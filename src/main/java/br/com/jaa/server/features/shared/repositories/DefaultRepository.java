package br.com.jaa.server.features.shared.repositories;

import br.com.jaa.server.features.shared.repositories.config.DataSourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

public abstract class DefaultRepository {

    @Resource(name = "dataSourceManager")
    protected DataSourceManager dataSourceManager;

    protected JdbcTemplate defaultJdbcTemplate;

    protected NamedParameterJdbcTemplate defaultNamedParameterJdbcTemplate;

    @PostConstruct
    public void init() {
        setDataSource();
    }

    protected void setDataSource() {
        DriverManagerDataSource ds = this.dataSourceManager.getDefaultDatasource();
        this.defaultNamedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ds);
        this.defaultJdbcTemplate = new JdbcTemplate(ds);
    }
}
