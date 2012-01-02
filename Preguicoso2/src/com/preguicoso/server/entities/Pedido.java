package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Unindexed;
import com.preguicoso.shared.entities.ItemCardapioBean;
import com.preguicoso.shared.entities.PedidoBean;

@Entity
@Cached
@Unindexed
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -668042019986705456L;

	@Id
	Long id;
	@Column
	Long idEstabelecimento;
	@Column
	String nomeCliente;
	@Column
	String rua;
	@Column
	String formaPagamento;
	List<ItemCardapio> listaItens;
	@Column
	Date timeStamp;

	public PedidoBean toBean() {
		PedidoBean pb = new PedidoBean();
		pb.setIdEstabelecimento(this.idEstabelecimento);
		pb.setNomeCliente(this.nomeCliente);
		pb.setRua(this.rua);
		pb.setFormaPagamento(this.formaPagamento);
		pb.setTimeStamp(this.timeStamp);
		List<ItemCardapioBean> listaItensBean = new ArrayList<ItemCardapioBean>();
		if (listaItens != null) {
			for (ItemCardapio item : this.listaItens) {
				listaItensBean.add(item.toBean());
			}
			pb.setListaItens(listaItensBean);
		}
		return pb;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<ItemCardapio> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ItemCardapio> listaItens) {
		this.listaItens = listaItens;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((formaPagamento == null) ? 0 : formaPagamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((idEstabelecimento == null) ? 0 : idEstabelecimento
						.hashCode());
		result = prime * result
				+ ((listaItens == null) ? 0 : listaItens.hashCode());
		result = prime * result
				+ ((nomeCliente == null) ? 0 : nomeCliente.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
		result = prime * result
				+ ((timeStamp == null) ? 0 : timeStamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (formaPagamento == null) {
			if (other.formaPagamento != null)
				return false;
		} else if (!formaPagamento.equals(other.formaPagamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idEstabelecimento == null) {
			if (other.idEstabelecimento != null)
				return false;
		} else if (!idEstabelecimento.equals(other.idEstabelecimento))
			return false;
		if (listaItens == null) {
			if (other.listaItens != null)
				return false;
		} else if (!listaItens.equals(other.listaItens))
			return false;
		if (nomeCliente == null) {
			if (other.nomeCliente != null)
				return false;
		} else if (!nomeCliente.equals(other.nomeCliente))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		return true;
	}

}
