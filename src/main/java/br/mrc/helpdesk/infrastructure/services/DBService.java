package br.mrc.helpdesk.infrastructure.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.mrc.helpdesk.domain.entities.Chamado;
import br.mrc.helpdesk.domain.entities.Cliente;
import br.mrc.helpdesk.domain.entities.Tecnico;
import br.mrc.helpdesk.domain.enums.Perfil;
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
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciarDB() {
		
		Tecnico tec1 = new Tecnico("MRC", "60275211800", "MRC@gmail.com", encoder.encode("senhamrc123"));
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico("GHP", "38647466802", "GHP@gmail.com", encoder.encode("senhaGHP"));
		Tecnico tec3 = new Tecnico("Renan", "37333767574", "Renan123@gmail.com", encoder.encode("Renan1234"));
		Tecnico tec4 = new Tecnico("REO", "40645823490", "REO@gmail.com", encoder.encode("senhaREO"));
		Tecnico tec5 = new Tecnico("VVV", "132423432", "VVV@gmail.com", encoder.encode("senhaVVV"));
		Tecnico tec6 = new Tecnico("CA1", "657567567", "CA1@gmail.com", encoder.encode("senhaCA1"));
		Tecnico tec7 = new Tecnico("BDA", "6456456456", "BDA@gmail.com", encoder.encode("senhaBDA"));
		
		Cliente cli1 = new Cliente("Debora","20653436300","Deb@gmail.com",encoder.encode("534"));
		Cliente cli2 = new Cliente("Microsoft","67727552518","Mic@gmail.com",encoder.encode("7727"));
		Cliente cli3 = new Cliente("Nestle","71785744704","Nes@gmail.com",encoder.encode("5744"));
		Cliente cli4 = new Cliente("Ipecacuanha","47722537502","Ipe@gmail.com",encoder.encode("4772"));
		
		Chamado c1 = new Chamado(null,Prioridade.ALTA,Status.ANDAMENTO,"Alteracao no codigo","Nada1",tec1,cli1);
		Chamado c2 = new Chamado(null,Prioridade.BAIXA,Status.ABERTO,"Alteracao no codigo","Nada2",tec2,cli2);
		Chamado c3 = new Chamado(null,Prioridade.MEDIA,Status.ENCERRADO,"Alteracao no codigo","Nada3",tec3,cli3);
		Chamado c4 = new Chamado(null,Prioridade.BAIXA,Status.ANDAMENTO,"Alteracao no codigo","Nada4",tec4,cli4);
		Chamado c5 = new Chamado(null,Prioridade.MEDIA,Status.ABERTO,"Alteracao no codigo","Nada5",tec1,cli2);
		Chamado c6 = new Chamado(null,Prioridade.ALTA,Status.ENCERRADO,"Alteracao no codigo","Nada6",tec2,cli3);
		Chamado c7 = new Chamado(null,Prioridade.BAIXA,Status.ABERTO,"Alteracao no codigo","Nada7",tec3,cli4);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1,tec2,tec3,tec4,tec5,tec6,tec7));
		clienteRepository.saveAll(Arrays.asList(cli1,cli2,cli3,cli4));
		chamadoRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7));
	}
}
