package com.preguicoso.server.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.entities.BairroBean;

@Entity
@Cached
public class Bairro {

	@Id long id;
	String nome;
	String cidade;
	List<Long> restaurantes;
	Date dataRegistro;
	Date ultimaAtualizacao;

	public BairroBean toBean() {
		BairroBean bean = new BairroBean();
		bean.setNome(this.nome);
		bean.setId(id);
		bean.setDataRegistro(this.dataRegistro);
		bean.setUltimaAtualizacao(this.ultimaAtualizacao);
		bean.setCidade(this.cidade);
		return bean;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public List<Long> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Long> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}
	

}
