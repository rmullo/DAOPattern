package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.jdbc.Connection; //Windows comentado

import config.ConnectionFactory;

public class CityDAO {
	
	public void create (City c) {
		
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO city ("	//Nome da tabela a inserir o dado
					+ "Name, "									//Todas as colunas das tabelas
					+ "CountryCode, "							//igual ao que está no banco de dados
					+ "District, "
					+ "Population) "
					+ "VALUES (?,?,?,?)");
			
			stmt.setString(1, c.getName());						//Primeira ?
			stmt.setString(2, c.getCountryCode());				//Segunda ?
			stmt.setString(3, c.getDistrict());
			stmt.setInt(4, c.getPopulation());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir no banco de dados: ", e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<City> readAll() {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<City> cidades = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM city");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				City c = new City();
				
				c.setId(rs.getInt("ID"));
				c.setName(rs.getString("Name"));
				c.setDistrict(rs.getString("District"));
				c.setPopulation(rs.getInt("Population"));
				c.setCountryCode(rs.getString("CountryCode"));
				
				cidades.add(c);
				
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler dados no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
		return cidades;
	}

	public City readById(int id) {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		City cidade = new City();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM city WHERE ID = ?");
			stmt.setInt(1, id);
			
			
			rs = stmt.executeQuery();
				
			while(rs.next()) {
				cidade.setId(rs.getInt("ID"));
				cidade.setName(rs.getString("Name"));
				cidade.setDistrict(rs.getString("District"));
				cidade.setPopulation(rs.getInt("Population"));
				cidade.setCountryCode(rs.getString("CountryCode"));
			}
			return cidade;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler dado por ID no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
		
	}
}
