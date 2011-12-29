package com.preguicoso.server.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;
import com.preguicoso.server.dao.CategoriaDAO;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.shared.entities.ItemCardapioBean;

@Entity
@Cached
@Unindexed
public class ItemCardapio {
	@Id
	Long id;
	@Indexed
	Long estabelecimentoId;

	public Long getEstabelecimentoId() {
		return estabelecimentoId;
	}

	public void setEstabelecimentoId(Long estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
	}

	@Indexed
	String categoria;
	@Indexed
	String nome;
	@Column
	String tipo;
	@Column
	Boolean disponivel;
	@Column
	String descricao;
	@Column
	Double preco;
	@Column
	Date dataRegistro;
	@Column
	Date ultimaAtualizacao;

	@Unindexed
	private String observacao;
	@Unindexed
	private int quantidade;

	public ItemCardapio() {
		this.nome = "";
		this.tipo = "";
		this.disponivel = false;
		this.descricao = "";
		this.preco = 0.0;
	}

	public ItemCardapio(String nome, String tipo, Boolean disponivel,
			String descricao, Double preco, Estabelecimento estabelecimento,
			Categoria categoria) {
		this.nome = nome;
		this.tipo = tipo;
		this.disponivel = disponivel;
		this.descricao = descricao;
		this.preco = preco;
		this.estabelecimentoId = estabelecimento.getId();
		this.categoria = categoria.getNome();
	}

	public ItemCardapioBean toBean() {
		ItemCardapioBean bean = new ItemCardapioBean();
		bean.setId(this.id);
		bean.setDescricao(this.descricao);
		bean.setNome(this.nome);
		bean.setPreco(this.preco);
		bean.setTipo(this.tipo);
		bean.setCategoriaBean(this.getCategoria().toBean());
		bean.setEstabelecimentoBean(this.estabelecimentoId);
		bean.setDataRegistro(this.dataRegistro);
		bean.setUltimaAtualizacao(this.ultimaAtualizacao);
		bean.setObservacao(this.observacao);
		bean.setQuantidade(this.quantidade);
		return bean;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isDisponivel() {
		return this.disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return this.preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Estabelecimento getEstabelecimento() {
		return (new EstabelecimentoDAO()).retrieve(this.estabelecimentoId);
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimentoId = estabelecimento.getId();
	}

	public Categoria getCategoria() {
		Categoria categoria = (new CategoriaDAO()).retrieve(this.categoria);
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria.getNome();
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

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

}
