package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.entities.ItemCardapioBean;
import com.preguicoso.shared.entities.PedidoBean;

@Entity
public class ItemPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -668042019986705456L;

	@Id
	Long id;
	
	Long idItem;
	String nomeItem;
	int quantidade;
	String observacao;
	
	
	public ItemPedido(Long idItem, String nomeItem, int quantidade,
			String observacao) {
		super();
		this.idItem = idItem;
		this.nomeItem = nomeItem;
		this.quantidade = quantidade;
		this.observacao = observacao;
	}

	public ItemCardapioBean toBean(){
		ItemCardapioBean i = new ItemCardapioBean();
		i.setId(idItem);
		i.setNome(nomeItem);
		i.setQuantidade(quantidade);
		i.setObservacao(observacao);
		return i;
	}

	public ItemPedido() {

	}

	public String toString()
	{
		return idItem+","+nomeItem+","+quantidade+","+getObservacao();
	}
	
	public ItemPedido(String string) {
		String[] item = string.split(",");
		idItem = new Long(item[0]);
		nomeItem = item[1];
		quantidade = new Integer(item[2]);
		//observacao = item[3];
	}

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

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = "observação: "+observacao;
	}


}
