package com.preguicoso.shared.entities.cardapio;

import java.io.Serializable;

public class IngredienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3817319478847637667L;
	private Long id;
	private Long idEstabelecimento;
	private Long preco;
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPreco() {
		return preco;
	}

	public void setPreco(Long preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

}
