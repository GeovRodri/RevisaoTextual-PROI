package br.edu.ifg.model;

import java.util.ArrayList;
import java.util.List;

public class Servico {
	
	private Integer id;
	private String descricao;
	private String caracteristicas;
	private List<ServicoValor> servicoValores = new ArrayList<>();
	
	public Servico(Integer id, String descricao, String caracteristicas) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.caracteristicas = caracteristicas;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getCaracteristicas() {
		return caracteristicas;
	}
	
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public List<ServicoValor> getServicoValores() {
		return servicoValores;
	}
	
	public void setServicoValores(List<ServicoValor> servicoValores) {
		this.servicoValores = servicoValores;
	}
}
