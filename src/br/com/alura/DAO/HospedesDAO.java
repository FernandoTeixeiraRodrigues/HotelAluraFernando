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

import br.com.alura.modelo.Hospedes;
import br.com.alura.modelo.Hospedes;


public class HospedesDAO {
	
	private Connection con;

	public HospedesDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public void guardar(Hospedes hospedes) {
		
		try {
			String sql= "INSERT INTO HOSPEDES (NOME, SOBRENOME, DATA_NASCIMENTO, NACIONALIDADE, TELEFONE, ID_RESERVA)"
					+ " VALUES(?,?,?,?,?,?) ";
			try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setString(1, hospedes.getNome());
				pstm.setString(2, hospedes.getSobrenome());
				pstm.setObject(3, hospedes.getDataNascimento());
				pstm.setString(4, hospedes.getNacionalidade());
				pstm.setString(5,hospedes.getTelefone());
				pstm.setInt(6, hospedes.getIdReserva());
				pstm.execute();
				try(ResultSet rst = pstm.getGeneratedKeys()){
					while(rst.next()) {
						 hospedes.setId(rst.getInt(1));
						 
						
						
					}
					
					
				}
				
				
			}
					
			
		} catch (SQLException e) {
			throw new RuntimeException("animal " + e.getMessage(),e );
		}
		
		
	}
	public List<Hospedes> mostraHospedes(){
		List<Hospedes> hospedes = new ArrayList<Hospedes>();
		try {
			String sql = "SELECT ID, NOME, SOBRENOME, DATA_NASCIMENTO, NACIONALIDADE, TELEFONE, ID_RESERVA FROM HOSPEDES";
			try(PreparedStatement pstm = con.prepareStatement(sql)){
		
				pstm.execute(sql);
				transformarResultado(hospedes,pstm);
				
			}
			return hospedes;
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
		
	}
	
	public List<Hospedes> buscarIdHospedes(String Id){
		List<Hospedes> hospedes = new ArrayList<Hospedes>();
		try {
			String sql = "SELECT ID, NOME, SOBRENOME, DATA_NASCIMENTO, NACIONALIDADE, TELEFONE, ID_RESERVA FROM HOSPEDES WHERE ID= ?";
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				pstm.setString(1, Id);
				pstm.execute();
				transformarResultado(hospedes,pstm);
				
			}
			return hospedes;
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		}
		
		
		public void AtualizarHospedes(String nome, String sobrenome, 
				LocalDate dataNascimento, String nacionalidade,
				String telefone, Integer idReserva, Integer id){
			
			try(PreparedStatement stm = con.prepareStatement(""
					+ "UPDATE HOSPEDES SET" + " NOME=?, SOBRENOME=?, "
					+ "DATA_NASCIMENTO=?, NACIONALIDADE=?, "
					+ "TELEFONE=?, ID_RESERVA=? WHERE ID=? ")) {
				stm.setString(1, nome);
				stm.setString(2, sobrenome);
				stm.setObject(3, dataNascimento);
				stm.setString(4, nacionalidade);
				stm.setString(5, telefone);
				stm.setInt(6, idReserva);
				stm.setInt(7, id);
				stm.execute();
				
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
				
			}	
			}
			
			public void Deletar(Integer id) {
				
				try(PreparedStatement stm = con.prepareStatement("DELETE FROM HOSPEDES WHERE ID=?")){
					
					stm.setInt(1, id);
					stm.execute();
					
				}catch (SQLException e) {
					throw new RuntimeException(e);
					
				}
			
		
		
		
		
	}
	private void transformarResultado(List<Hospedes> hospedes, PreparedStatement pstm) 
			throws SQLException {
		try (ResultSet rst = pstm.executeQuery()){
			
			while(rst.next()) {
				
				int Id = rst.getInt("ID");
				String Nome = rst.getString("NOME");
				String Sobrenome = rst.getString("SOBRENOME");
			    LocalDate DataNascimento= rst.getDate("DATA_NASCIMENTO").toLocalDate().plusDays(1);
				String Nacionalidade = rst.getString("NACIONALIDADE");
				String Telefone = rst.getString("TELEFONE");
				int IdReserva = rst.getInt("ID_RESERVA");
				
				Hospedes hospede = new Hospedes ( Id, Nome, Sobrenome, DataNascimento, Nacionalidade, Telefone, IdReserva);
				hospedes.add(hospede);
				
				
			}
			
		}
		
		
		
	}

}
