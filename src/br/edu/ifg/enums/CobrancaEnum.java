package br.edu.ifg.enums;

import java.util.EnumSet;

public enum CobrancaEnum {
	
	POR_PAGINA("0", "Iremos cobrar pelo valor de páginas que o seu arquivo possui."),
	POR_PALAVRA("1", "Iremos cobrar por cada palavra que o seu arquivo possui."),
	POR_LAUDA("2", "Iremos realizar a cobrança por cada lauda do arquivo.");
	
	private String id;
	private String descricao;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	CobrancaEnum (String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public static CobrancaEnum lookup(String id) {
		for (final CobrancaEnum cobranca : EnumSet.allOf(CobrancaEnum.class)) {
			if (cobranca.id.equals(id)) {
				return cobranca;
			}
		}
		
		return null;
	}
}
