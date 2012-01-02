package com.preguicoso.server.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

@Entity
@Cached
@Unindexed
public class ItemCardapio {
	@Id
	Long id;
	@Indexed
	Long estabelecimentoId;
	@Column
	int numero;
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
	public Long getEstabelecimentoId() {
		return estabelecimentoId;
	}

	public void setEstabelecimentoId(Long estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
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

	public ItemCardapio(ItemCardapioBean i) {
		this.nome = i.getNome();
		this.tipo = i.getTipo();
		this.disponivel = i.isDisponivel();
		this.descricao = i.getDescricao();
		this.preco = i.getPreco();
		this.estabelecimentoId = i.getEstabelecimentoBean();
		this.categoria = i.getCategoriaBean().getNome();
	}

	public ItemCardapioBean toBean() {
		ItemCardapioBean bean = new ItemCardapioBean();
		bean.setId(this.id);
		bean.setDescricao(this.descricao);
		bean.setNome(this.nome);
		bean.setPreco(this.preco);
		bean.setTipo(this.tipo);
		bean.setCategoriaBean(new CategoriaBean(getCategoria()));
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

	public String getCategoria() {
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
