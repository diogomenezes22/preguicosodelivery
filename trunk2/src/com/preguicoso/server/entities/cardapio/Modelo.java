package com.preguicoso.server.entities.cardapio;

import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.entities.cardapio.ModeloBean;

@Entity
public class Modelo {

	@Id
	Long id;
	List<Long> listaIdOpcoes;
	List<Long> listaIdIngredientes;
	List<Long> idSubItem;
	Integer quantidade;

	public ModeloBean toBean() {
		ModeloBean mb = new ModeloBean();
		mb.setId(id);
		mb.setListaIdIngredientes(listaIdIngredientes);
		mb.setListaIdOpcoes(listaIdOpcoes);
		mb.setIdSubItem(idSubItem);
		mb.setQuantidade(quantidade);
		return mb;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
