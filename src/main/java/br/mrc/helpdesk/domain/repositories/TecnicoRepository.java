package br.mrc.helpdesk.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mrc.helpdesk.domain.entities.Tecnico;
/**
 * Interface JPA do Tecnico.
 * AULA: 7
 */
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
	
}
