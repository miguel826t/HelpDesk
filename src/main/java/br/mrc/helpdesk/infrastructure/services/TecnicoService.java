package br.mrc.helpdesk.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mrc.helpdesk.domain.entities.Pessoa;
import br.mrc.helpdesk.domain.entities.Tecnico;
import br.mrc.helpdesk.domain.repositories.PessoaRepository;
import br.mrc.helpdesk.domain.repositories.TecnicoRepository;
import br.mrc.helpdesk.infrastructure.services.exceptions.DataIntegrityViolationException;
import br.mrc.helpdesk.infrastructure.services.exceptions.ObjectNotFoundException;
/**
 * Service do Tecnico.
 * AULA: 11
 */
@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicos;
	
	@Autowired
	private PessoaRepository pessoasRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicos.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Tecnico não encontrado! Id: "+id));
	}
	
	public List<Tecnico> findAll(){
		return tecnicos.findAll();
	}

	public Tecnico create(Tecnico tecnico) {
		validaPorCpfeEmail(tecnico);
		return tecnicos.save(tecnico); 
	}

	private void validaPorCpfeEmail(Tecnico tecnico) {
		Optional<Pessoa> obj = pessoasRepository.findByCpf(tecnico.getCpf());
		if(obj.isPresent() && obj.get().getId() != tecnico.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoasRepository.findByEmail(tecnico.getEmail());
		if(obj.isPresent() && obj.get().getId() != tecnico.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
		
	}
}