package com.preguicoso.server.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;
import com.preguicoso.shared.entities.CategoriaBean;


@Entity
@Cached
@Unindexed
public class Categoria implements Serializable {
	@Id @Indexed String nome;
	@Column Long estabelecimentoId;	
	@Column Date dataRegistro;
	@Column Date ultimaAtualizacao;

	public Categoria(){

	}
	public Long getEstabelecimentoId() {
		return estabelecimentoId;
	}
	public void setEstabelecimentoId(Long estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
	}
	public Categoria(String nome){
		this.nome = nome;
	}
	public Categoria(CategoriaBean i) {
		setNome(i.getNome());
		setDataRegistro(i.getDataRegistro());
		setUltimaAtualizacao(i.getUltimaAtualizacao());
		setEstabelecimentoId(i.getEstabelecimentoId());
	}
	public CategoriaBean toBean(){
		CategoriaBean bean = new CategoriaBean();
		bean.setNome(this.nome);
		bean.setDataRegistro(this.dataRegistro);
		bean.setUltimaAtualizacao(this.ultimaAtualizacao);
		if(estabelecimentoId!=null)			
			bean.setEstabelecimentoId(this.estabelecimentoId);
		return bean;
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


}