package br.mrc.helpdesk.domain.enums;
/**
 * Enum.
 * AULA: 5
 */
public enum Status {
	
	ABERTO(0,"ABERTO"),
	ANDAMENTO(1,"ANDAMENTO"),
	ENCERRADO(2,"ENCERRADO");
	
	private Integer codigo;
	private String descricao;
	
	private Status(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static Status toEnum(Integer codigo) {
		if(codigo == null)
			return null;
		
		for(Status p : Status.values()) {
			if(codigo.equals(p.getCodigo()))
				return p;
		}
		
		throw new IllegalArgumentException("Status inválido");
	}
}
