package br.mrc.helpdesk.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
/**
 * Cliente.
 * AULA: 6
 */
@Entity
public class Cliente extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente") //Um chamado só pode ter um cliente
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(String nome, String cpf, String email, String senha) {
		super(nome, cpf, email, senha);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
}
