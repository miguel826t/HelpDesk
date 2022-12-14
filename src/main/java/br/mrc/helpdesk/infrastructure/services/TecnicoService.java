package br.mrc.helpdesk.infrastructure.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mrc.helpdesk.domain.entities.Tecnico;
import br.mrc.helpdesk.domain.repositories.TecnicoRepository;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicos;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicos.findById(id);
		return obj.orElse(null);
	}
	
}