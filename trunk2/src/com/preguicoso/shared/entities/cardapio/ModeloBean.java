package com.preguicoso.shared.entities.cardapio;

import java.io.Serializable;
import java.util.List;

public class ModeloBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3811796403355486254L;

	private Long id;
	private List<Long> listaIdOpcoes;
	private List<Long> listaIdIngredientes;
	private List<Long> idSubItem;
	private Integer quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getListaIdOpcoes() {
		return listaIdOpcoes;
	}

	public void setListaIdOpcoes(List<Long> listaIdOpcoes) {
		this.listaIdOpcoes = listaIdOpcoes;
	}

	public List<Long> getListaIdIngredientes() {
		return listaIdIngredientes;
	}

	public void setListaIdIngredientes(List<Long> listaIdIngredientes) {
		this.listaIdIngredientes = listaIdIngredientes;
	}

	public List<Long> getIdSubItem() {
		return idSubItem;
	}

	public void setIdSubItem(List<Long> idSubItem) {
		this.idSubItem = idSubItem;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
