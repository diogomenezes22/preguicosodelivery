package com.preguicoso.server.entities.cardapio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.preguicoso.shared.entities.cardapio.CategoriaBean;

@Entity
public class Categoria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8305189428165868607L;

	@Id
	Long id;
	String nome;
	Long estabelecimentoId;

	public Categoria() {
	}

	public Categoria(CategoriaBean ib) {
		setId(ib.getId());
		setNome(ib.getNome());
		setEstabelecimentoId(ib.getEstabelecimentoId());
	}

	public CategoriaBean toBean() {
		CategoriaBean bean = new CategoriaBean();
		bean.setId(id);
		bean.setNome(nome);
		bean.setEstabelecimentoId(estabelecimentoId);
		return bean;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEstabelecimentoId() {
		return estabelecimentoId;
	}

	public void setEstabelecimentoId(Long estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
	}

}