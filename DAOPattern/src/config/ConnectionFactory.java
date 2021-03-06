package config;

import java.sql.*;

public abstract class ConnectionFactory {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://20.20.1.171:3306/world";
	private static final String USER = "aluno";
	private static final String PASS = "aluno";
	
	public static Connection getConnection() {
		 try {
			 
			 Class.forName(DRIVER);
			 return DriverManager.getConnection(URL, USER, PASS);
			 
		 } catch (SQLException | ClassNotFoundException e) {
			 throw new RuntimeException("Erro na conexão com o banco: ", e);
		 }
	}
	
	public static void closeConnection(Connection con) {
		try {	
			if(con!=null) {
				con.close();	
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar conexão com o banco: ", e);
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) {
		
		closeConnection(con);
		
		try {
			
			if(stmt!=null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar declaração do banco: ", e);
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		
		closeConnection(con, stmt);
		
		try {
			
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar resultado do banco: ", e);
		}
	}
}
