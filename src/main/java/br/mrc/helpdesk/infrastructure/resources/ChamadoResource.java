package br.mrc.helpdesk.infrastructure.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.mrc.helpdesk.domain.dtos.ChamadoDTO;
import br.mrc.helpdesk.domain.entities.Chamado;
import br.mrc.helpdesk.infrastructure.services.ChamadoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
	
	@Autowired
	private ChamadoService chamadoService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		Chamado obj = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll(){
		List<Chamado> chamados = chamadoService.findAll();
		List<ChamadoDTO> chamadosDTO = chamados.stream().map(c-> new ChamadoDTO(c)).collect(Collectors.toList());
		return ResponseEntity.ok().body(chamadosDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO objDTO) {
		Chamado newObj = chamadoService.update(id, objDTO);
		return ResponseEntity.ok().body(new ChamadoDTO(newObj));
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO){
		Chamado obj = chamadoService.create(chamadoDTO);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
}
