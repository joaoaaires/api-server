package br.com.jaa.server.features.usuario.repositories;

import br.com.jaa.server.features.usuario.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioCrudRepository extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    boolean existsByEmail(String email);

}
