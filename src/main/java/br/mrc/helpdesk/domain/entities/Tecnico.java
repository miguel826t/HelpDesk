package br.mrc.helpdesk.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.mrc.helpdesk.domain.dtos.TecnicoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
/**
 * Tecnico.
 * AULA: 6
 */
@Entity
public class Tecnico extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "tecnico") //Um chamado só pode ter um tecnico
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
	}

	public Tecnico(String nome, Integer cpf, String email, String senha) {
		super(nome, cpf, email, senha);
	}
	
	public Tecnico(TecnicoDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfils = obj.getPerfils().stream().map(p -> p.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	

}
