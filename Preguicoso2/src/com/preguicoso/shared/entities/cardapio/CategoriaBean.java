package com.preguicoso.shared.entities.cardapio;

import java.io.Serializable;

public class CategoriaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2222136075691599745L;

	private Long id;
	private String nome;
	private Long estabelecimentoId;

	public CategoriaBean() {

	}

	public CategoriaBean(String nome) {
		this.setNome(nome);
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getEstabelecimentoId() {
		return estabelecimentoId;
	}

	public void setEstabelecimentoId(Long estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
