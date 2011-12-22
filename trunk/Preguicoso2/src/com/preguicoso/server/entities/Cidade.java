package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;
import com.preguicoso.server.dao.EstadoDAO;
import com.preguicoso.shared.entities.CidadeBean;

@Entity
@Cached
@Unindexed
public class Cidade implements Serializable {

	@Id @Indexed String nome;
	@Indexed String estado;
	@Column Date dataRegistro;
	@Column Date ultimaAtualizacao;

	public Cidade() {
		this.nome = "";
		this.estado = null;
	}

	public Cidade(String nome, Estado estado) {
		this();
		this.nome = nome;
		this.estado = estado.getNome();
	}

	public CidadeBean toBean() {
		CidadeBean bean = new CidadeBean();
		bean.setEstadoBean(this.getEstado().toBean());
		bean.setNome(this.nome);
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

	public Estado getEstado() {
		Estado estado = (new EstadoDAO()).retrieve(this.estado);
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado.getNome();
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
