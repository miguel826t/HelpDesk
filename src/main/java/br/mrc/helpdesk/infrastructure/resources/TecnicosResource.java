package br.mrc.helpdesk.infrastructure.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mrc.helpdesk.domain.dtos.TecnicoDTO;
import br.mrc.helpdesk.domain.entities.Tecnico;
import br.mrc.helpdesk.infrastructure.services.TecnicoService;
/**
 * Controller para Tecnico.
 * AULA: 11
 */
@RestController
@RequestMapping(value="/tecnicos")
public class TecnicosResource {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico tecnico = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
	}
}
