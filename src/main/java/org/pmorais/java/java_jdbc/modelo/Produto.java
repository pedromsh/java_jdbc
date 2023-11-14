package org.pmorais.java.java_jdbc.modelo;

import java.util.Date;

public class Produto {

	private Integer id;
	private String nome;
	private Integer preco;
	private Date dataRegistro;
	
	public Produto() {
		
	}
	
	public Produto(Integer id, String nome, Integer preco, Date dataRrgistro) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.dataRegistro = dataRrgistro;
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
	
	public Integer getPreco() {
		return preco;
	}
	public void setPreco(Integer preco) {
		this.preco = preco;
	}
	
	public Date getDataRegistro() {
		return dataRegistro;
	}
	
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", dataRegistro=" + dataRegistro + "]";
	}
	
	
}
