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
			stmt = con.prepareStatement("INSERT INTO city "
					+ "(Name, "
					+ "CountryCode, "
					+ "District, "
					+ "Population) "
					+ "VALUES "
					+ "(?,?,?,?)");
			
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getCountryCode());
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
			
			return cidades;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler dados no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
	}
	
}
