package br.com.jaa.server.core.database;

import br.com.jaa.server.features.shared.repositories.config.DataSourceManager;
import br.com.jaa.server.features.usuario.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class CreateDatabaseInitializator {

    @Autowired
    private UsuarioRepository usuarioRepository;

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    private void init() {
        createDatabase();
    }

    public void createDatabase() {
        log.info("> create table usuario");
        usuarioRepository.createTable();
        log.info("> create table usuario fim");
    }

}
