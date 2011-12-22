package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;

public class EstadoBean implements Serializable {

	private static final long serialVersionUID = 1921334149005836325L;

	private String nome;
	private String sigla;
	private PaisBean paisBean;
	private Date dataRegistro;
	private Date ultimaAtualizacao;

	public EstadoBean() {
		this.nome = "";
		this.sigla = "";
		this.paisBean = new PaisBean();
	}

	public EstadoBean(String nome, String sigla, PaisBean paisBean) {
		this();
		this.nome = nome;
		this.sigla = sigla;
		this.paisBean = paisBean;
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

	public PaisBean getPaisBean() {
		return this.paisBean;
	}

	public void setPaisBean(PaisBean paisBean) {
		this.paisBean = paisBean;
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
