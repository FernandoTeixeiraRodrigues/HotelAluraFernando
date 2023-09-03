package br.com.alura.controles;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import br.com.alura.DAO.ReservaDAO;
import br.com.alura.conexão.ConexaoBaseDeDados;
import br.com.alura.modelo.Reservas;

public class ReservasControles {
	
	private ReservaDAO reservaD;
	
	public ReservasControles () throws SQLException {
		
		Connection con= new ConexaoBaseDeDados().recuperarConexao();
		
		this.reservaD = new ReservaDAO(con);
	}
	
	public void guardar(Reservas reserva) {
		
		this.reservaD.guardar(reserva);
		
		
	}
	
	public List<Reservas> mostra(){
		
		return this.reservaD.mostrar();
		
		
		
	}
	public List<Reservas> buscar(String Id){
		
		return this.reservaD.buscarIdReservas(Id);
		
		
		
	}
	
	public void atualizarReservas(LocalDate DataE, LocalDate DataS, String Valor, String FormaPaga, Integer Id) {
		this.reservaD.Atualizar(DataE, DataS, Valor, FormaPaga, Id);
	}
	
	public void Deletar (Integer Id) {
		this.reservaD.Deletar(Id);
	}

}

	


