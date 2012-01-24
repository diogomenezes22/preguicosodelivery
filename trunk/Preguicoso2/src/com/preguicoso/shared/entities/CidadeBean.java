package com.preguicoso.shared.entities;

import java.io.Serializable;

public class CidadeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8990859460300638059L;
	Long id;
	String nome;

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

}
