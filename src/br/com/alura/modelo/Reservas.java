package br.com.alura.modelo;

import java.time.LocalDate;

public class Reservas {
	
	
	private Integer Id;
	private LocalDate DataE;
	private LocalDate DataS;
	private String Valor;
	private String FormaPaga;
	

	
	public Reservas(Integer Id,LocalDate DataE, LocalDate DataS, String Valor, String FormaPaga) {
		super();
		this.Id = Id;
		this.DataE = DataE;
		this.DataS = DataS;
		this.Valor = Valor;
		this.FormaPaga = FormaPaga;
	}





	public Integer getId() {
		return Id;
	}






	public void setId(Integer id) {
		this.Id = id;
	}






	public LocalDate getDataE() {
		return DataE;
	}






	public void setDataE(LocalDate DataE) {
		this.DataE = DataE;
	}






	public LocalDate getDataS() {
		return DataS;
	}






	public void setDataS(LocalDate dataS) {
		this.DataS = dataS;
	}






	public String getValor() {
		return Valor;
	}






	public void setValor(String valor) {
		this.Valor = valor;
	}






	public String getFormaPaga() {
		return FormaPaga;
	}






	public void setFormaPaga(String FormaPaga) {
		this.FormaPaga = FormaPaga;
	}






	
	
	

}
