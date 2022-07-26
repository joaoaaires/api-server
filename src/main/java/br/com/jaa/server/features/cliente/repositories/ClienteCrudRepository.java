package br.com.jaa.server.features.cliente.repositories;

import br.com.jaa.server.features.cliente.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteCrudRepository extends CrudRepository<Cliente, Long> {
}
