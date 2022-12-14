package br.mrc.helpdesk.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mrc.helpdesk.domain.entities.Cliente;
/**
 * Interface JPA do cliente.
 * AULA: 7
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
}
