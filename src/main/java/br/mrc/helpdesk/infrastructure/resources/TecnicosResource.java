package br.mrc.helpdesk.infrastructure.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.mrc.helpdesk.domain.dtos.TecnicoDTO;
import br.mrc.helpdesk.domain.entities.Tecnico;
import br.mrc.helpdesk.infrastructure.services.TecnicoService;
import jakarta.validation.Valid;
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
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<Tecnico> list = tecnicoService.findAll();
		
		List<TecnicoDTO> listDTO = list.stream().map(t -> new TecnicoDTO(t)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
		objDTO.setId(null);
		Tecnico tecnico = new Tecnico(objDTO);
		tecnicoService.create(tecnico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,@RequestBody TecnicoDTO objDTO){
		objDTO.setId(id);
		Tecnico tecnico = new Tecnico(objDTO);
		TecnicoDTO objRetorno = new TecnicoDTO(tecnicoService.update(tecnico));
		return ResponseEntity.ok().body(objRetorno);
	}
	
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
