package br.com.alura.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.alura.conexão.ConexaoBaseDeDados;

public class Usuarios {
	
	private String Nome;
	private String Senha;
	
	public Usuarios(String Nome, String Senha) {
		
		this.Nome = Nome;
		this.Senha = Senha;
		
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}
	
	public static boolean validarUsuario(String Nome, String Senha) {
		ConexaoBaseDeDados con = new ConexaoBaseDeDados();
		Connection conex = null;
		PreparedStatement state = null;
		ResultSet result = null;
		
		try {
			conex = con.recuperarConexao();
			state = conex.prepareStatement("SELECT * FROM USUARIOS WHERE NOME=? AND SENHA=?");
			state.setString(1, Nome);
			state.setString(2, Senha);
			result = state.executeQuery();
			return result.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}finally {
			try {
				if (result !=null)
					result.close();
				if(state !=null)
					state.close();
				if (conex !=null);
					conex.close();
					
				
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			
			}
		}

		
		
	
	}
}
