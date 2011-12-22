package com.preguicoso.server.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;
import com.preguicoso.server.dao.CidadeDAO;
import com.preguicoso.shared.entities.BairroBean;

@Entity
@Cached
@Unindexed
public class Bairro {

	@Id @Indexed String cep;
	@Indexed String nome;
	@Indexed String cidade;
	@Column Date dataRegistro;
	@Column Date ultimaAtualizacao;

	public Bairro() {
		this.cep = "";
		this.nome = "";
		this.cidade = "";
	}

	public BairroBean toBean() {
		BairroBean bean = new BairroBean();
		bean.setNome(this.nome);
		bean.setCep(this.cep);
		bean.setDataRegistro(this.dataRegistro);
		bean.setUltimaAtualizacao(this.ultimaAtualizacao);
		bean.setCidadeBean(this.getCidade().toBean());
		return bean;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cidade getCidade() {
		return (new CidadeDAO()).retrieve(this.cidade);
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade.getNome();
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
