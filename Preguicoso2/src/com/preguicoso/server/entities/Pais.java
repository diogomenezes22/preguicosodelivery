package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;
import com.preguicoso.shared.entities.PaisBean;


@Entity
@Cached
@Unindexed
public class Pais implements Serializable {

	@Id @Indexed String nome;
	@Indexed String sigla;
	@Column Date dataRegistro;
	@Column Date ultimaAtualizacao;

	public Pais() {
		this.nome = "";
		this.sigla = "";
	}

	public Pais(String nome, String sigla) {
		this();
		this.nome = nome;
		this.sigla = sigla;
	}

	public PaisBean toBean() {
		PaisBean bean = new PaisBean();
		bean.setNome(this.nome);
		bean.setSigla(this.sigla);
		bean.setDataRegistro(this.dataRegistro);
		bean.setUltimaAtualizacao(this.ultimaAtualizacao);
		return bean;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
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
