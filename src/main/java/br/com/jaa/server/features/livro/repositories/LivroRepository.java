package br.com.jaa.server.features.livro.repositories;

import br.com.jaa.server.features.livro.entities.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long> {

}
