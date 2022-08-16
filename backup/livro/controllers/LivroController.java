package br.com.jaa.server.features.livro.controllers;

import br.com.jaa.server.features.livro.entities.Livro;
import br.com.jaa.server.features.livro.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @SchemaMapping(typeName = "Mutation", value = "createLivro")
    public Livro create(@Argument String descricao) {
        Livro livro = new Livro(null, descricao);
        livro.setDataHoraInc(new Timestamp(System.currentTimeMillis()));
        return livroRepository.save(livro);
    }

    @SchemaMapping(typeName = "Query", value = "readAllLivro")
    public Iterable<Livro> readAll() {
        return livroRepository.findAll();
    }

}
