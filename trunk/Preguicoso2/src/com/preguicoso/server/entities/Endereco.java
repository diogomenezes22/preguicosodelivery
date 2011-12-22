package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;
import com.preguicoso.server.dao.BairroDAO;
import com.preguicoso.shared.entities.EnderecoBean;


@Entity
@Cached
@Unindexed
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id Long id;
	@Column String rua;
	@Column Integer numero;
	@Column String complemento;
	@Indexed String cepBairro;
	@Column Date dataRegistro;
	@Column Date ultimaAtualizacao;

	public Endereco() {
		this.rua = "";
		this.numero = null;
		this.complemento = "";
		this.cepBairro = "";
	}

	public Endereco(String rua, Integer numero, String complemento, String cepBairro) {
		this();
		this.rua = rua;
		this.numero = numero;
		this.complemento = "";
		this.cepBairro = cepBairro;
	}

	public EnderecoBean toBean() {
		EnderecoBean bean = new EnderecoBean();
		bean.setId(this.id);
		bean.setNumero(this.numero);
		bean.setComplemento(this.complemento);
		bean.setBairroBean(this.getBairro().toBean());
		bean.setRua(this.rua);
		bean.setDataRegistro(this.dataRegistro);
		bean.setUltimaAtualizacao(this.ultimaAtualizacao);
		return bean;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Bairro getBairro() {
		Bairro bairro = (new BairroDAO()).retrieveByCep(this.cepBairro);
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.cepBairro = bairro.getCep();
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
