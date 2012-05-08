package com.preguicoso.shared.entities;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;

@Entity
public class Estoque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6955923465734579707L;

	@Id
	Long id;
	@Indexed
	Long idItem;
	@Indexed
	Double preco;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	@Indexed
	Long idEstabelecimento;

	public Estoque() {
	
	}
}
