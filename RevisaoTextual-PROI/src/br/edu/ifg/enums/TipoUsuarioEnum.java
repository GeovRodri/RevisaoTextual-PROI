package br.edu.ifg.enums;

import java.util.EnumSet;

public enum TipoUsuarioEnum {
	
	NORMAL("0", "Usuario normal"),
	ADMIN("1", "Administrador");
	
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
	
	TipoUsuarioEnum (String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public static TipoUsuarioEnum lookup(String id) {
		for (final TipoUsuarioEnum cobranca : EnumSet.allOf(TipoUsuarioEnum.class)) {
			if (cobranca.id.equals(id)) {
				return cobranca;
			}
		}
		
		return null;
	}
}
