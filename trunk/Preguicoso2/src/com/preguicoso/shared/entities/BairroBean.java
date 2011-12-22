package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;

public class BairroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cep;
	private String nome;
	private CidadeBean cidadeBean;
	private Date dataRegistro;
	private Date ultimaAtualizacao;

	public BairroBean() {
		this.cep = "";
		this.nome = "";
		this.cidadeBean = new CidadeBean();
	}

	public BairroBean(String cep, String nome, CidadeBean cidadeBean) {
		this.cep = cep;
		this.nome = nome;
		this.cidadeBean = cidadeBean;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CidadeBean getCidadeBean() {
		return this.cidadeBean;
	}

	public void setCidadeBean(CidadeBean cidadeBean) {
		this.cidadeBean = cidadeBean;
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

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
