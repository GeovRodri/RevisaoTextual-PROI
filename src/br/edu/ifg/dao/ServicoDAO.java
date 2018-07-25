package br.edu.ifg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifg.jdbc.ConnectionFactory;
import br.edu.ifg.model.Servico;
import br.edu.ifg.model.ServicoValor;

public class ServicoDAO {
	
	private Connection connection;

	//Realiza a conexão com o banco de dados
	public ServicoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	//Metodo responsável por retornar uma lista de serviços
	public List<Servico> getAll() {
		try {
			 List<Servico> servicos = new ArrayList<>();//lista que ira receber os serviços
			
			String sql = "SELECT id, descricao, caracteristicas FROM servico";// SQL que será executada no banco
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			// rs recebe o resultado da consulta feita no banco
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				//percorre o resultado, adicionando os serviços na lista
				Servico servico = new Servico(rs.getInt("id"), rs.getString("descricao"), rs.getString("caracteristicas"));
				servico.setServicoValores(this.getValores(servico.getId()));
				
				servicos.add(servico);
			}
			
			rs.close();
			stmt.close();
			return servicos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*Busca e Retorna um servico especifico, baseado no id 
	*@param id identificador do servico
	*/
	public Servico getServico(Integer id){	
		try {
			String sql = "SELECT id, descricao, caracteristicas FROM servico WHERE id = ?";
			Servico servico = null;	//criação de um objeto vazio para receber os dados
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				servico = new Servico();
				// insere os dados no objeto 
				servico.setId(rs.getInt("id"));
				servico.setDescricao(rs.getString("descricao"));
				servico.setCaracteristicas(rs.getString("caracteristicas"));
				servico.setServicoValores(this.getValores(servico.getId()));
			}
			rs.close();
			stmt.close();
			return servico;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*Retrona os Valores de um determinado serviço
	*@param idServico identifica o servico alvo da busca
	*/
	public List<ServicoValor> getValores(Integer idServico) {
		try {
			List<ServicoValor> valores = new ArrayList<>();
			String sql = "SELECT id, forma_pagamento, valor FROM servico_valor WHERE id_servico = " + idServico;
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ServicoValor servicoValor = new ServicoValor( rs.getInt("id"),  rs.getString("forma_pagamento"), 
						rs.getDouble("valor"));
				valores.add(servicoValor);
			}
			
			rs.close();
			stmt.close();
			return valores;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* Faz a alteração de um servico já existente
	*@param servico objeto com os novos dados
	*/
	public void alterarServico(Servico servico) {
		String sql = "UPDATE servico SET descricao = ?, caracteristicas = ?, servicoValores= ? WHERE id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, servico.getDescricao());
			stmt.setString(2, servico.getCaracteristicas());
			// stmt.setDouble(3, servico.getServicoValores());
			stmt.setInt(4, servico.getId());

			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*Exclui um serviço especifico
	*@param id identificador do serviço
	*/
	public void excluirServico(Integer id) {
		String sql = "DELETE FROM servico WHERE id = ?";
	
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*Persiste um novo serviço e seus valores na base de dados
	*@param servico objeto que contém os dados a serem inseridos
	*/
	public void adicionaServico(Servico servico) {
		String sql = "INSERT INTO servico (descricao, caracteristicas, servicoValores) values (?,?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, servico.getDescricao());
			stmt.setString(2, servico.getCaracteristicas());
			// stmt.setString(3, servico.getServicoValores());
			// stmt.setString(5, servico.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*Persiste um serviço na base de dados
	*@param servico contem os dados para persistencia
	*/
	public void salvarServico(Servico servico){
	String sql = "INSERT INTO servico (descricao, caracteristicas) values (?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, servico.getDescricao());
			stmt.setString(2, servico.getCaracteristicas());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
}
}
