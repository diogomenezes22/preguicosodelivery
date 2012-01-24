package com.preguicoso.server.entities;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.entities.CidadeBean;

@Entity
public class Cidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 504947229871992792L;
	@Id
	Long id;
	String nome;

	public Cidade() {

	}

	public Cidade(CidadeBean cidadeBean) {
		this.id = cidadeBean.getId();
		this.nome = cidadeBean.getNome();
	}

	public CidadeBean toBean() {
		CidadeBean cb = new CidadeBean();
		cb.setId(this.id);
		cb.setNome(this.nome);
		return cb;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
