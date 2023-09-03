package br.com.alura.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import br.com.alura.modelo.Reservas;

public class ReservaDAO {
	
	
	private Connection con;

	public ReservaDAO(Connection con) {
		super();
		this.con = con;
	}
	 
	
	public void guardar(Reservas reservas) {
		
		try {
			
			String sql = "INSERT INTO RESERVAS (DATA_ENTRADA, DATA_SAIDA, VALOR, FORMA_DE_PAGAMENTO)"
					+ "VALUES(?,?,?,?) ";
			try (PreparedStatement pstm = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)){
				
				pstm.setObject(1, reservas.getDataE());
				pstm.setObject(2,reservas.getDataS());
				pstm.setString(3, reservas.getValor());
				pstm.setString(4,reservas.getFormaPaga());
				pstm.executeUpdate();
				
				
			try(ResultSet rst = pstm.getGeneratedKeys()){
				while(rst.next()) {
					reservas.setId(rst.getInt(1));	
				}	
			}
			
			}
		} catch (SQLException e) {
			throw new RuntimeException();
			
			
			
		}
		
		
		
	}
	
	public List<Reservas> mostrar(){
		List<Reservas> reservas = new ArrayList<Reservas>();
		try {
			String sql = "SELECT id, data_entrada, data_saida, "
					+ "valor, forma_de_pagamento FROM RESERVAS";
			try(PreparedStatement pstm = con.prepareStatement(sql)){
		
				pstm.execute(sql);
				transformarResultado(reservas,pstm);
				
			}
			return reservas;
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
		
	}
	
	
	public List<Reservas> buscarIdReservas(String Id){
		List<Reservas> reservas = new ArrayList<Reservas>();
		try {
			String sql = "SELECT id, data_entrada, data_saida, valor, "
					+ "forma_de_pagamento FROM RESERVAS WHERE id= ? ";
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				pstm.setString(1, Id);
				pstm.execute();
				transformarResultado(reservas,pstm);
				
			}
			return reservas;
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		}
		
		public void Atualizar(LocalDate DataE, LocalDate DataS, String Valor, String FormaPaga, Integer Id) {
			try(PreparedStatement stm = con.prepareStatement("UPDATE RESERVAS SET " + "DATA_ENTRADA= ?, "
					+ "DATA_SAIDA=?,VALOR=?, FORMA_DE_PAGAMENTO=? WHERE ID=?")){
				stm.setObject(1, java.sql.Date.valueOf(DataE));
				stm.setObject(2, java.sql.Date.valueOf(DataS));
				stm.setString(3, Valor);
				stm.setString(4, FormaPaga);
				stm.setInt(5, Id);
				stm.execute();
				
				
			}catch (SQLException e) {
				throw new RuntimeException("animal " + e.getMessage() );
							
			
			}
		
		}
		public void Deletar (Integer Id){
			
			try {
				Statement state = con.createStatement();
				state.execute("SET FOREIGN_KEY_CHECKS=0");
				PreparedStatement stm = con.prepareStatement("DELETE FROM "
					+ "RESERVAS WHERE ID = ?");
				stm.setInt(1, Id);
				stm.execute();
				state.execute("SET FOREIGN_KEY_CHECKS=1");
				
			}catch(SQLException e) {
				 throw new RuntimeException("animal " + e.getMessage());
				
				
			}
			
		}
		
		
		
		
	
	

	
	private void transformarResultado(List<Reservas> reservas, PreparedStatement pstm) 
			throws SQLException {
		try (ResultSet rst = pstm.getResultSet()){
			
			while(rst.next()) {
				
				int Id = rst.getInt("id");
				LocalDate DataE = rst.getDate("data_entrada").toLocalDate().plusDays(1);
				LocalDate DataS = rst.getDate("data_saida").toLocalDate().plusDays(1);
				String Valor = rst.getString("valor");
				String FormaPaga = rst.getString("forma_de_pagamento");
				
				Reservas produto = new Reservas ( Id, DataE, DataS, Valor, FormaPaga);
				reservas.add(produto);
				
				
			}
			
		}
		
		
		
	}
	
}
