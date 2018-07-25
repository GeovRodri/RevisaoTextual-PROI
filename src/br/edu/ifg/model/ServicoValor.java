package br.edu.ifg.model;

import br.edu.ifg.enums.CobrancaEnum;

public class ServicoValor {
	
	private Integer id;
	private Integer idServico;
	private String nomeServico;
	private CobrancaEnum formaPagamento;
	private Double valor;
	
	public ServicoValor(Integer id, String formaPagamento, Double valor) {
		super();
		this.id = id;
		this.formaPagamento = CobrancaEnum.lookup(formaPagamento);
		this.valor = valor;
	}
	
	public ServicoValor() {
		super();
	}

	public CobrancaEnum getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(CobrancaEnum formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public String getNomeServico() {
		return nomeServico;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
}