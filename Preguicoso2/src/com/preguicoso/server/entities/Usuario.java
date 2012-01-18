package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.entities.UsuarioBean;

@Entity
@Cached
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	String email;
	Date dataRegistro;
	Date ultimaAtualizacao;
	boolean cadastrado = false;
	String CEP;

	public Usuario() {
		this.email = "";

		Calendar c = Calendar.getInstance();
		this.dataRegistro = c.getTime();
		this.ultimaAtualizacao = c.getTime();
	}

	public Usuario(UsuarioBean a) {
		this.email = a.getEmail();
		this.CEP = a.getCEP();
		this.cadastrado = a.isCadastrado();
	}

	public UsuarioBean toBean() {
		UsuarioBean bean = new UsuarioBean();
		bean.setEmail(this.email);
		bean.setCadastrado(this.cadastrado);
		bean.setCEP(this.CEP);
		bean.setDataRegistro(this.dataRegistro);
		bean.setUltimaAtualizacao(this.ultimaAtualizacao);
		return bean;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
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

	public boolean isCadastrado() {
		return this.cadastrado;
	}

	public void setCadastrado(boolean cadastrado) {
		this.cadastrado = cadastrado;
	}

	public String getCEP() {
		return this.CEP;
	}

	public void setCEP(String CEP) {
		this.CEP = CEP;
	}
}
