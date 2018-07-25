
package br.edu.ifg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifg.jdbc.ConnectionFactory;
import br.edu.ifg.model.ServicoValor;

public class ServicoValorDAO {


	private Connection connection;

	public ServicoValorDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void removerValorServico(Integer id){
		String sql = "DELETE servicoValores FROM servico WHERE idServico = '?'";

		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adicionaServicoValor(ServicoValor valor) {
		String sql = "INSERT INTO servico_valor (forma_pagamento, valor,id_servico) values (?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, valor.getFormaPagamento());
			stmt.setDouble(2, valor.getValor());
			stmt.setInt(3, valor.id_servico());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
