package br.com.alura.controles;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import br.com.alura.DAO.HospedesDAO;
import br.com.alura.conexão.ConexaoBaseDeDados;
import br.com.alura.modelo.Hospedes;

public class HospedesControles {
	
	
	private HospedesDAO hospedesDao;
	
	public HospedesControles() throws SQLException {
		
		Connection con = new ConexaoBaseDeDados().recuperarConexao();
		this.hospedesDao = new HospedesDAO(con);
		
	}
	
	
	public void guardar(Hospedes hospedes) {
		
		this.hospedesDao.guardar(hospedes);
		
	}

	public List<Hospedes> mostraHospedes(){
		return this.hospedesDao.mostraHospedes();

}

	public List<Hospedes> buscarIdHospedes(String Id){
		
		return this.hospedesDao.buscarIdHospedes(Id);

}
	public void AtualizarHospedes(String nome, String sobrenome, 
				LocalDate dataNascimento, String nacionalidade,
				String telefone, Integer idReserva, Integer id) {
		
		this.hospedesDao.AtualizarHospedes(nome, sobrenome, dataNascimento, nacionalidade, telefone, idReserva, id);
		
	}
	public void Deletar(Integer idReserva) {
		this.hospedesDao.Deletar(idReserva);
	}

}