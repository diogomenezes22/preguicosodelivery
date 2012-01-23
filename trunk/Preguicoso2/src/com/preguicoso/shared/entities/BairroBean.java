package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

public class BairroBean implements Serializable {

	/**
	 * 
	 */
	@Id long id;
	private String nome;
	private String cidade;
	private List<Long> restaurantes;
	private Date dataRegistro;
	private Date ultimaAtualizacao;
	private static final long serialVersionUID = 1L;
	
	public BairroBean() {

	}

	public BairroBean(String nome, String cidadeBean) {
		this.nome = nome;
		this.cidade = cidade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public List<Long> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Long> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}
	
	
}
