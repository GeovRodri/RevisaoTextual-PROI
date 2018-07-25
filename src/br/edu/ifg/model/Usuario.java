package br.edu.ifg.model;

import br.edu.ifg.enums.TipoUsuarioEnum;

public class Usuario {
	//Informações do usuario
	private int id;
	private String cpf;
	private String nome;
	private String senha;
	private String email;
	private TipoUsuarioEnum tipo;//Usuario comum ou Adm

	private String uf;
	private String localidade;
	private String cep;
	private String bairro;
	private String logradouro;
	private String numero;

	public Usuario() {
	}
	//Construtor
	public Usuario(int id, String nome, String email, String cpf, String uf, String localidade, 
			String cep, String bairro, String logradouro, String numero) {
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		
		// EndereÃ§o
		this.uf = uf;
		this.localidade = localidade;
		this.cep = cep;
		this.bairro = bairro;
		this.logradouro = logradouro;
		this.numero = numero;
	}
	//Sobrecarga de constutor
	public Usuario(int id, String nome, String email, String tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.tipo = TipoUsuarioEnum.lookup(tipo);
	}
	//Metodos Getters e Setters
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
