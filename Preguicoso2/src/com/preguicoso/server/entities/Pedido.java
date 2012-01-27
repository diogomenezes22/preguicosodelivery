package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.entities.ItemCardapioBean;
import com.preguicoso.shared.entities.PedidoBean;

@Entity
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -668042019986705456L;

	@Id
	Long id;
	Long idEstabelecimento;
	String nomeCliente;
	String rua;
	String formaPagamento;
	Date timeStamp;
	String bairro;
	String complemento;
	RegistroStatusPedido status;

	String listaItensJSON;

	public PedidoBean toBean() {
		PedidoBean pb = new PedidoBean();
		pb.setId(this.id);
		pb.setIdEstabelecimento(this.idEstabelecimento);
		pb.setNomeCliente(this.nomeCliente);
		pb.setRua(this.rua);
		pb.setFormaPagamento(this.formaPagamento);
		pb.setTimeStamp(this.timeStamp);
		pb.setBairro(this.bairro);
		pb.setComplemento(this.complemento);
		pb.setStatus(this.status);

		try {
			if (listaItensJSON != null) {
				JSONArray ja = new JSONArray(listaItensJSON);
				List<ItemCardapioBean> listaRec = new ArrayList<ItemCardapioBean>();
				JSONObject jo;
				for (int i = 0; i < ja.length(); i++) {
					jo = new JSONObject(ja.get(i).toString());
					ItemCardapioBean item = new ItemCardapioBean();
					item.setId(jo.getLong("id"));
					item.setNumero(jo.getInt("numero"));
					item.setNome(jo.getString("nome"));
					item.setObservacao(jo.getString("observacao"));
					item.setQuantidade(jo.getInt("quantidade"));
					listaRec.add(item);
				}
				pb.setListaItens(listaRec);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return pb;
	}

	public String getListaItensJSON() {
		return listaItensJSON;
	}

	public void setListaItensJSON(List<ItemCardapioBean> lista) {

		List<JSONObject> listaJSON = new ArrayList<JSONObject>();
		JSONObject jo;
		for (ItemCardapioBean item : lista) {
			jo = new JSONObject(item);
			listaJSON.add(jo);
		}

		JSONArray ja = new JSONArray(listaJSON);
		this.listaItensJSON = ja.toString();
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

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setListaItensJSON(String listaItensJSON) {
		this.listaItensJSON = listaItensJSON;
	}

	public RegistroStatusPedido getStatus() {
		return status;
	}

	public void setStatus(RegistroStatusPedido status) {
		this.status = status;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result
				+ ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result
				+ ((formaPagamento == null) ? 0 : formaPagamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((idEstabelecimento == null) ? 0 : idEstabelecimento
						.hashCode());
		result = prime * result
				+ ((listaItensJSON == null) ? 0 : listaItensJSON.hashCode());
		result = prime * result
				+ ((nomeCliente == null) ? 0 : nomeCliente.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
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
		if (listaItensJSON == null) {
			if (other.listaItensJSON != null)
				return false;
		} else if (!listaItensJSON.equals(other.listaItensJSON))
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
		if (status != other.status)
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		return true;
	}

}
