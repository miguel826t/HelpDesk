package br.mrc.helpdesk.infrastructure.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mrc.helpdesk.domain.entities.Chamado;
import br.mrc.helpdesk.domain.entities.Cliente;
import br.mrc.helpdesk.domain.entities.Tecnico;
import br.mrc.helpdesk.domain.enums.Prioridade;
import br.mrc.helpdesk.domain.enums.Status;
import br.mrc.helpdesk.domain.repositories.ChamadoRepository;
import br.mrc.helpdesk.domain.repositories.ClienteRepository;
import br.mrc.helpdesk.domain.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instanciarDB() {
		
		Tecnico tec1 = new Tecnico("Mrc", "123456789", "mrc@gmail.com", "senhaMRc");
		Cliente cli1 = new Cliente("Debora","94234153","Db@gmail.com","dbk123");
		
		Chamado c1 = new Chamado(null,Prioridade.ALTA,Status.ABERTO,"Alteracao no codigo","Nada",tec1,cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}
}
