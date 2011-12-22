package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;

public class CidadeBean implements Serializable {

	private static final long serialVersionUID = 1921334149005836325L;

	private String nome;
	private EstadoBean estadoBean;
	private Date dataRegistro;
	private Date ultimaAtualizacao;

	public CidadeBean() {
		this.nome = "";
		this.estadoBean = new EstadoBean();
	}

	public CidadeBean(String nome, EstadoBean estadoBean) {
		this.nome = nome;
		this.estadoBean = estadoBean;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EstadoBean getEstadoBean() {
		return this.estadoBean;
	}

	public void setEstadoBean(EstadoBean estadoBean) {
		this.estadoBean = estadoBean;
	}

	public Date getUltimaAtualizacao() {
		return this.ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public Date getDataRegistro() {
		return this.dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
}
