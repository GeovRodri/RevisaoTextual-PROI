package br.edu.ifg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifg.enums.TipoUsuarioEnum;
import br.edu.ifg.jdbc.ConnectionFactory;
import br.edu.ifg.model.Usuario;

public class UsuarioDAO {

	private Connection connection;
	//estabelece conexão com a base de dados
	public UsuarioDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	/*Persiste um novo usuário na base
	*@param usuario objeto com os dados a serem inseridos
	*/
	public void adiciona(Usuario usuario) {
		//criação do comando SQL a ser executado
		String sql = "INSERT INTO usuario (cpf, nome, senha, email, tipo, uf, localidade, cep, bairro, logradouro, numero) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			//inserção dos valores no SQL
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

	/* Retorna um usuário identificado pelo id
	*@param id identificador do usuario
	*/
	public Usuario buscar(Integer id) {
		try {
			Usuario user = null;
			//cria um objeto vazio para receber os dados
			String sql = ("SELECT * FROM usuario WHERE id = ?");
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				//chama o metodo responsável por inserir os dados
				user = preencherUsuario(rs);
			}
			
			rs.close();
			stmt.close();
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/* Insere os dados em um usuário
	*@param rs resultado de uma consulta SQL contendo dados de um usuário
	*/
	private Usuario preencherUsuario(ResultSet rs) throws SQLException {
		Usuario user;
		user = new Usuario();
		//inserção dos dados
		user.setId(rs.getInt("id"));
		user.setCpf(rs.getString("cpf"));
		user.setNome(rs.getString("nome"));
		user.setSenha(rs.getString("senha"));
		user.setEmail(rs.getString("email"));
		user.setTipo(TipoUsuarioEnum.lookup(rs.getString("tipo")));
		
		// endereço
		user.setUf(rs.getString("uf"));
		user.setLocalidade(rs.getString("localidade"));
		user.setCep(rs.getString("cep"));
		user.setBairro(rs.getString("bairro"));
		user.setLogradouro(rs.getString("logradouro"));
		user.setNumero(rs.getString("numero"));
		return user;
	}

	/* Realiza a busca de um usuário usando o email
	*@param email email do usuario almejado
	*/
	public Usuario buscarPorEmail(String email) {
		try {
			Usuario user = null;
			
			String sql = ("SELECT * FROM usuario WHERE email = ?");
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				user = preencherUsuario(rs);
			}

			rs.close();
			stmt.close();
			
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* Altera os dados de um usuário
	*@param objeto contendo os  novos dados
	*/
	public void alterar(Usuario usuario) {
		String sql = "UPDATE usuario SET "
										+ "cpf = ?, "
										+ "nome = ?, "
										+ "email = ?, "
										+ "uf = ?, "
										+ "localidade = ?, "
										+ "cep = ?, "
										+ "bairro = ?, "
										+ "logradouro = ?, "
										+ "numero = ? "
					+ "WHERE id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getEmail());
			
			// Endereço
			stmt.setString(4, usuario.getUf());
			stmt.setString(5, usuario.getLocalidade());
			stmt.setString(6, usuario.getCep());
			stmt.setString(7, usuario.getBairro());
			stmt.setString(8, usuario.getLogradouro());
			stmt.setString(9, usuario.getNumero());
			
			stmt.setInt(10, usuario.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/* Altera a senha de um usuári
	*@param usuario usuario alvo da alteração
	*/
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
	
	/* Remove um usuario da base de dados
	*@param id indentificador do usuario alvo
	*/
	public void excluirUsuario(Integer id) {
		String sql = "DELETE FROM usuario WHERE id = ?";

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
