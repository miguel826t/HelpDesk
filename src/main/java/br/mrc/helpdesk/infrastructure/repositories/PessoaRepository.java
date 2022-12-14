package br.mrc.helpdesk.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mrc.helpdesk.domain.entities.Pessoa;
/**
 * Interface JPA da pessoa.
 * AULA: 7
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
}
