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

public class ServicoValorDAO {
	
	private Connection connection;

	public ServicoValorDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public List<Servico> getAll() {
		try {
			 List<Servico> servicos = new ArrayList<>();
			
			String sql = "SELECT id, descricao, caracteristicas FROM servico";
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
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
	
	
	public List<ServicoValor> buscarPorServico(Integer idServico) {
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
}
