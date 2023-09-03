package br.com.alura.conexão;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConexaoBaseDeDados {
	
	public DataSource dataSource;
	public ConexaoBaseDeDados() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura_ft?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("root");
		
		comboPooledDataSource.setMaxPoolSize(15);
		this.dataSource = comboPooledDataSource;
		
	}
	
	public Connection recuperarConexao () throws SQLException {
		
	  return this.dataSource.getConnection();
	  
	
	
		
		
	}

}
