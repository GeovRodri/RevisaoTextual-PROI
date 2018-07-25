package br.edu.ifg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Classe responsável por criar a conexão com a base de dados
*/
public class ConnectionFactory {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/revisao-textual?useTimezone=true&serverTimezone=UTC";
			String user = "root";
			String passw = "";
			return DriverManager.getConnection(url, user, passw);
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
