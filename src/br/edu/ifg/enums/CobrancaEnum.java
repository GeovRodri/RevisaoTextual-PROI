package br.edu.ifg.enums;

import java.util.EnumSet;

public enum CobrancaEnum {
	
	POR_PAGINA("0", "Por pagina."),
	POR_PALAVRA("1", "Por palavra."),
	POR_LAUDA("2", "Por lauda.");
	
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
