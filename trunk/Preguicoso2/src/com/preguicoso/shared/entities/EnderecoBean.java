package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;

public class EnderecoBean implements Serializable {

	private static final long serialVersionUID = 1921334149005836325L;

	private Long id;
	private String cep;
	private String rua;
	private Integer numero;
	private String complemento;
	private BairroBean bairroBean;
	private Date ultimaAtualizacao;
	private Date dataRegistro;

	public EnderecoBean() {
		this.cep = "";
		this.rua = "";
		this.numero = null;
		this.complemento = "";
		this.bairroBean = new BairroBean();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return this.rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Date getUltimaAtualizacao() {
		return this.ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public BairroBean getBairroBean() {
		return this.bairroBean;
	}

	public void setBairroBean(BairroBean bairroBean) {
		this.bairroBean = bairroBean;
	}

	public Date getDataRegistro() {
		return this.dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
}
