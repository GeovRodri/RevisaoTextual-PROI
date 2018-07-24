package br.edu.ifg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifg.enums.TipoUsuarioEnum;
import br.edu.ifg.jdbc.ConnectionFactory;
import br.edu.ifg.model.Usuario;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Usuario usuario) {
		String sql = "INSERT INTO usuario (cpf, nome, senha, email, tipo) values (?,?,?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getEmail());
			stmt.setString(5, usuario.getTipo().getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Usuario buscar(Integer id) {
		try {
			Usuario user = null;
			String sql = ("SELECT * FROM usuario WHERE id = ?");
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new Usuario();
				
				user.setId(rs.getInt("id"));
				user.setCpf(rs.getString("cpf"));
				user.setNome(rs.getString("nome"));
				user.setSenha(rs.getString("senha"));
				user.setEmail(rs.getString("email"));
				user.setTipo(TipoUsuarioEnum.lookup(rs.getString("tipo")));
			}
			
			rs.close();
			stmt.close();
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Usuario buscarPorEmail(String email) {
		try {
			Usuario user = null;
			
			String sql = ("SELECT * FROM usuario WHERE email = ?");
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				user = new Usuario();
				
				user.setId(rs.getInt("id"));
				user.setCpf(rs.getString("cpf"));
				user.setNome(rs.getString("nome"));
				user.setSenha(rs.getString("senha"));
				user.setEmail(rs.getString("email"));
				user.setTipo(TipoUsuarioEnum.lookup(rs.getString("tipo")));
			}

			rs.close();
			stmt.close();
			
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Usuario> getLista() {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				
				usuario.setId(rs.getInt("id"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTipo(TipoUsuarioEnum.lookup(rs.getString("tipo")));
				
				usuarios.add(usuario);
			}
			
			rs.close();
			stmt.close();
			
			return usuarios;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterar(Usuario usuario) {
		String sql = "UPDATE usuario SET cpf = ?, nome = ?, email = ? WHERE id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getEmail());
			stmt.setInt(4, usuario.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterarSenha(Usuario usuario) {
		String sql = "UPDATE usuario SET senha = ? WHERE id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getSenha());
			stmt.setInt(2, usuario.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluirUsuario(Integer id) {
		String sql = "DELETE usuario WHERE id = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
