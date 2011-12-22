package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;

public class PaisBean implements Serializable {

	private static final long serialVersionUID = 1921334149005836325L;

	private String nome;
	private String sigla;
	private Date dataRegistro;
	private Date ultimaAtualizacao;

	public PaisBean() {
		this.nome = "";
		this.sigla = "";
	}

	public PaisBean(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
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

	public Date getUltimaAtualizacao() {
		return this.ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getDataRegistro() {
		return this.dataRegistro;
	}
}
