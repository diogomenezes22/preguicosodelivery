package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.preguicoso.shared.entities.CategoriaBean;

@Entity
public class Categoria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8305189428165868607L;
	@Id
	String nome;
	Long estabelecimentoId;
	Date dataRegistro;
	Date ultimaAtualizacao;

	public Categoria() {
	}

	public Long getEstabelecimentoId() {
		return estabelecimentoId;
	}

	public void setEstabelecimentoId(Long estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
	}

	public Categoria(String nome) {
		this.nome = nome;
	}

	public Categoria(CategoriaBean i) {
		setNome(i.getNome());
		setDataRegistro(i.getDataRegistro());
		setUltimaAtualizacao(i.getUltimaAtualizacao());
		setEstabelecimentoId(i.getEstabelecimentoId());
	}

	public CategoriaBean toBean() {
		CategoriaBean bean = new CategoriaBean();
		bean.setNome(this.nome);
		bean.setDataRegistro(this.dataRegistro);
		bean.setUltimaAtualizacao(this.ultimaAtualizacao);
		if (estabelecimentoId != null)
			bean.setEstabelecimentoId(this.estabelecimentoId);
		return bean;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataRegistro() {
		return this.dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getUltimaAtualizacao() {
		return this.ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

}