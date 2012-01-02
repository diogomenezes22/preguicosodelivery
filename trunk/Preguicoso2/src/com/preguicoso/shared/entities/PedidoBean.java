package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PedidoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3151557261100923942L;
	private Long idEstabelecimento;
	private String nomeCliente;
	private String rua;
	private String formaPagamento;
	private List<ItemCardapioBean> listaItens;
	private Date timeStamp;
	private String bairro;

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
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

	public List<ItemCardapioBean> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ItemCardapioBean> listaItens) {
		this.listaItens = listaItens;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
