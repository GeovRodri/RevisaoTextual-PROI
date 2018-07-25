
package br.edu.ifg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifg.jdbc.ConnectionFactory;
import br.edu.ifg.model.ServicoValor;

public class ServicoValorDAO {


	private Connection connection;
	//cria conexão com a base de dados
	public ServicoValorDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	/*Remove os valores de servico da base pelo id
	*@param id identificador do serviço
	*/
	public void removerValorServico(Integer id){
		
		String sql = "DELETE servicoValores FROM servico WHERE idServico = '?'";
		//criação do comando SQL a ser executado no banco
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);//insere no SQL o id do servico alvo
			stmt.execute();//execução do comando SQL
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	/* Adicina os valores de um serviço a base
	*@param valor objeto contendo os valores
	*/
	public void adicionaServicoValor(ServicoValor valor) {
		String sql = "INSERT INTO servico_valor (forma_pagamento, valor,id_servico) values (?,?,?)";
			
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			//adiciona ao comando os valores a serem inseridos na base
			stmt.setString(1, valor.getFormaPagamento().getId());
			stmt.setDouble(2, valor.getValor());
			stmt.setInt(3, valor.getIdServico());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
