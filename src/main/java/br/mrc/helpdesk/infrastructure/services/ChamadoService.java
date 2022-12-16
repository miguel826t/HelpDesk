package br.mrc.helpdesk.infrastructure.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mrc.helpdesk.domain.dtos.ChamadoDTO;
import br.mrc.helpdesk.domain.entities.Chamado;
import br.mrc.helpdesk.domain.entities.Cliente;
import br.mrc.helpdesk.domain.entities.Tecnico;
import br.mrc.helpdesk.domain.enums.Status;
import br.mrc.helpdesk.domain.repositories.ChamadoRepository;
import br.mrc.helpdesk.infrastructure.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamados;
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private TecnicoService tecnicoService;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> chamado = chamados.findById(id);
		return chamado.orElseThrow(()-> new ObjectNotFoundException("Chamado não encontrado. ID: "+id));
	}
	
	public List<Chamado> findAll(){
		return chamados.findAll();
	}
	
	public Chamado create(ChamadoDTO chamadoDTO) {
		return chamados.save(validarECarregarChamado(chamadoDTO));
	}
	
	public Chamado update(Integer id,ChamadoDTO chamadoDTO) {
		chamadoDTO.setId(id);
		Chamado oldChamado = findById(id);
		oldChamado = validarECarregarChamado(chamadoDTO);
		return chamados.save(oldChamado);
	}
	
	public Chamado validarECarregarChamado(ChamadoDTO chamadoDTO) {
		Tecnico tecnico = tecnicoService.findById(chamadoDTO.getIdTecnico());
		Cliente cliente = clienteService.findById(chamadoDTO.getIdCliente());
		
		if(chamadoDTO.getStatus().equals(Status.ENCERRADO.getCodigo())) {
			chamadoDTO.setDataFechamento(LocalDate.now());
		}
		
		return new Chamado(chamadoDTO,tecnico,cliente);
	}
	
}
