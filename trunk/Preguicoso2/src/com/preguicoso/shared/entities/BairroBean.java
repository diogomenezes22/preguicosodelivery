package com.preguicoso.shared.entities;

import java.io.Serializable;

public class BairroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 651535741006857843L;

	Long id;
	String nome;
	Long idCidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

}