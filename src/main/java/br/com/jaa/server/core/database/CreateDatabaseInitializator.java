package br.com.jaa.server.core.database;

import br.com.jaa.server.features.usuario.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CreateDatabaseInitializator {

    final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsuarioRepository usuarioRepository;

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
