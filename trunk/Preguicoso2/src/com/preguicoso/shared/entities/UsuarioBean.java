package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;

public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -6787661440312707953L;

	private String email;
	private String cep;
	private String url;
	private boolean cadastrado;
	private Date dataRegistro;
	private Date ultimaAtualizacao;

	public UsuarioBean() {
		this.email = "";
		this.cep = "";
		this.url = "";
		this.cadastrado = false;
	}

	public boolean isCadastrado() {
		return this.cadastrado;
	}

	public void setCadastrado(boolean cadastrado) {
		this.cadastrado = cadastrado;
	}

	public String getCEP() {
		return this.cep;
	}

	public void setCEP(String cep) {
		this.cep = cep;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
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

	public void setUrl(String createLogoutURL) {
		this.url = createLogoutURL;
	}

	public String getUrl() {
		return this.url;
	}
}
