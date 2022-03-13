package br.com.jaa.server.core.database;

import br.com.jaa.server.features.shared.repositories.config.DataSourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class CreateDatabaseInitializator {

    @Resource(name = "dataSourceManager")
    protected DataSourceManager dataSourceManager;

    Logger log;
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void init() {
        log = LoggerFactory.getLogger(this.getClass());
        setDataSource();
        createDatabase();
    }

    public void createDatabase() {
        log.info("> create table usuario");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS usuario (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "nome VARCHAR(255) NOT NULL,\n" +
                "email VARCHAR(255) NOT NULL UNIQUE,\n" +
                "password VARCHAR(255) NOT NULL,\n" +
                "situacao VARCHAR(1) NOT NULL,\n" +
                "datahorainc DATETIME NOT NULL,\n" +
                "datahoraalt DATETIME,\n" +
                "datahoradel DATETIME\n" +
                ");");
        log.info("> create table usuario fim");
    }

    public void setDataSource() {
        DriverManagerDataSource ds = dataSourceManager.getDefaultDatasource();
        jdbcTemplate = new JdbcTemplate(ds);
    }
}
