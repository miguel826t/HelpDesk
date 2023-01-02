package br.mrc.helpdesk.infrastructure.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.mrc.helpdesk.domain.dtos.ClienteDTO;
import br.mrc.helpdesk.domain.entities.Cliente;
import br.mrc.helpdesk.infrastructure.services.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/clientes")
public class ClientesResource {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		ClienteDTO cliente = new ClienteDTO(clienteService.findById(id));
		return ResponseEntity.ok().body(cliente);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> clientes = clienteService.findAll();
		
		List<ClienteDTO> clientesDTO = clientes.stream().map(c -> new ClienteDTO(c)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clientesDTO);
	}
	
	@PreAuthorize("hasAnyRole('TECNICO')")
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){
		objDTO.setId(null);
		Cliente cliente = new Cliente(objDTO);
		clienteService.create(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('TECNICO')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@RequestBody ClienteDTO objDTO){
		objDTO.setId(id);
		Cliente cliente = new Cliente(objDTO);
		ClienteDTO objRetorno = new ClienteDTO(clienteService.update(cliente));
		return ResponseEntity.ok().body(objRetorno);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
