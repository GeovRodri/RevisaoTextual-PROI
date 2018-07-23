package br.edu.ifg.model;

import br.edu.ifg.enums.TipoUsuarioEnum;

public class Usuario {

	private int id;
	private String cpf;
	private String nome;
	private String senha;
	private String email;
	private TipoUsuarioEnum tipo;

	public Usuario() {
	}
	
	public Usuario(int id, String nome, String email, String tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.tipo = TipoUsuarioEnum.lookup(tipo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuarioEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuarioEnum tipo) {
		this.tipo = tipo;
	}
}
