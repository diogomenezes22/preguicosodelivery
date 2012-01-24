package com.preguicoso.server.entities;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.entities.BairroBean;

@Entity
@Cached
public class Bairro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6955923465734579707L;

	@Id
	Long id;
	String nome;
	Long idCidade;

	public Bairro() {
	}

	public BairroBean toBean() {
		BairroBean bb = new BairroBean();
		bb.setId(this.id);
		bb.setNome(this.nome);
		bb.setIdCidade(this.idCidade);
		return bb;
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

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

}
