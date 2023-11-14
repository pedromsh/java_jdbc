package org.pmorais.java.java_jdbc.modelo;

public class Categoria {

	private Integer id;
	private String nome;
	
	public Categoria() {
		
	}
	
	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
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

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + "]";
	}
	
	
	
	
}
