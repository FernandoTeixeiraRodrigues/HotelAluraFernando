package br.com.alura.modelo;

import java.time.LocalDate;

public class Hospedes {
	
	public Hospedes(Integer id, String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade,
			String telefone, Integer idReserva) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		IdReserva = idReserva;
	}


	private Integer id;
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private String nacionalidade;
	private String telefone;
	private Integer IdReserva;
	
	
	public Hospedes(String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone,
		 Integer IdReserva) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.IdReserva = IdReserva;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getNacionalidade() {
		return nacionalidade;
	}


	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Integer getIdReserva() {
		return IdReserva;
	}


	public void setIdReserva(Integer IdReserva) {
		this.IdReserva = IdReserva;
	}


	public String getSobrenome() {
		return sobrenome;
	}


	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	
	
	
	

}
