package br.mrc.helpdesk.domain.entities;

import java.util.ArrayList;
import java.util.List;
/**
 * Cliente.
 * AULA: 6
 */
public class Cliente extends Pessoa{
	
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(String nome, Integer cpf, String email, String senha) {
		super(nome, cpf, email, senha);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
}
