package com.preguicoso.server.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;
import com.preguicoso.server.dao.EstabelecimentoDAO;

@Entity
@Cached
@Unindexed
public class Cardapio {
	@Id Long id;
	@Indexed Long estabelecimentoId;
	@Column Date dataRegistro;
	@Column Date ultimaAtualizacao;

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Estabelecimento getEstabelecimento() {
		Estabelecimento estabelecimento = (new EstabelecimentoDAO()).retrieve(this.estabelecimentoId);
		return estabelecimento;
	}
	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimentoId = estabelecimento.getId();
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
