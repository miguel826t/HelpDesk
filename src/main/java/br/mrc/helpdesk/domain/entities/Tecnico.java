package br.mrc.helpdesk.domain.entities;

import java.util.ArrayList;
import java.util.List;
/**
 * Tecnico.
 * AULA: 6
 */
public class Tecnico extends Pessoa{

	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
	}

	public Tecnico(String nome, Integer cpf, String email, String senha) {
		super(nome, cpf, email, senha);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
