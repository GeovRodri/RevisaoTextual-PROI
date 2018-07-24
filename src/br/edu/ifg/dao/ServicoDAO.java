package br.edu.ifg.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.sis.internal.converter.StringConverter.Integer;
import org.jsoup.Connection;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import br.edu.ifg.jdbc.ConnectionFactory;
import br.edu.ifg.model.Servico;
import br.edu.ifg.model.ServicoValor;
import br.edu.ifg.model.Usuario;

public class ServicoDAO {
	
	private Connection connection;

	public ServicoDAO() {
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

	public void alterarServico(Servico servico) {
		String sql = "UPDATE servico SET descricao = ?, caracteristicas = ?, servicoValores= ? WHERE id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, servico.getDescricao());
			stmt.setString(2, servico.getCaracteristicas());
			stmt.setDouble(3, servico.getServicoValores());
			stmt.setInt(4, servico.getId());

			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
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
	public void adicionaServico(Servico servico) {
		String sql = "INSERT INTO servico (descricao, caracteristicas, servicoValores) values (?,?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, servico.getDescricao());
			stmt.setString(2, servico.getCaracteristicas());
			stmt.setString(3, servico.getServicoValores());
			stmt.setString(5, servico.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
