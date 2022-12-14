package br.mrc.helpdesk.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mrc.helpdesk.domain.entities.Chamado;
/**
 * Interface JPA do chamado.
 * AULA: 7
 */
public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{
	
}
