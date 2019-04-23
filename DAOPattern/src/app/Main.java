package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import config.ConnectionFactory;
import model.City;
import model.CityDAO;

public class Main {

	public static void main(String[] args) throws SQLException {
		City c = new City();
		CityDAO dao = new CityDAO();
		/*
			c.setName("NomeTeste");
			c.setCountryCode("Cod");
			c.setDistrict("DistritoTeste");
			c.setPopulation(123456);
			
			dao.create(c);
		*/
		
		List<City> cidades = dao.readAll(); 
		for (City cid : cidades) {
			System.out.println("ID: "+cid.getId());
			System.out.println("Nome: "+cid.getName());
			System.out.println("Estado: "+cid.getDistrict());
			System.out.println("Código do País: "+cid.getCountryCode());
			System.out.println("População: "+cid.getPopulation());
			System.out.println("***************************************************");
		}
	}

}
