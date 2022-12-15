package br.mrc.helpdesk.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mrc.helpdesk.domain.entities.Pessoa;
/**
 * Interface JPA da pessoa.
 * AULA: 7
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
	
	Optional<Pessoa> findByCpf(Integer cpf);
	Optional<Pessoa> findByEmail(String email);
}
