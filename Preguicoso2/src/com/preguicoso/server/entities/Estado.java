package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;
import com.preguicoso.server.dao.PaisDAO;
import com.preguicoso.shared.entities.EstadoBean;


@Entity
@Cached
@Unindexed
public class Estado implements Serializable {
	@Id @Indexed String nome;
	@Indexed String sigla;
	@Indexed String pais;
	@Column Date dataRegistro;
	@Column Date ultimaAtualizacao;

	public Estado() {
		this.nome = "";
		this.sigla = "";
		this.pais = "";
	}

	public Estado(String nome, String sigla, Pais pais) {
		this();
		this.nome = nome;
		this.sigla = sigla;
		this.pais = pais.getNome();
	}

	public EstadoBean toBean() {
		EstadoBean bean = new EstadoBean();
		bean.setNome(this.nome);
		bean.setSigla(this.sigla);
		bean.setDataRegistro(this.dataRegistro);
		bean.setUltimaAtualizacao(this.ultimaAtualizacao);
		bean.setPaisBean(this.getPais().toBean());
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

	public Pais getPais() {
		Pais pais = (new PaisDAO()).retrieve(this.pais);
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais.getNome();
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
