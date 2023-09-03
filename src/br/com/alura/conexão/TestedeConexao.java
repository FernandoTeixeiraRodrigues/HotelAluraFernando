package br.com.alura.conexão;

import java.sql.Connection;
import java.sql.SQLException;

public class TestedeConexao {

	public static void main(String[] args) throws SQLException {
		
		ConexaoBaseDeDados con = new ConexaoBaseDeDados();
		Connection cone = con.recuperarConexao();
		
	System.out.println("Conexão Concluida");
	cone.close();
	
	System.out.println("Conexão Fechada");
	
		

	}

}
