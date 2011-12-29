package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;


public class CategoriaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2222136075691599745L;

	private String nome;
	private Date dataRegistro;
	private Long estabelecimentoId;
	private Date ultimaAtualizacao;

	public CategoriaBean(){
		this.nome = "";
	}
	public CategoriaBean(String nome){
		this.setNome(nome);
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public Long getEstabelecimentoId() {
		return estabelecimentoId;
	}
	public void setEstabelecimentoId(Long estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
	}
}
