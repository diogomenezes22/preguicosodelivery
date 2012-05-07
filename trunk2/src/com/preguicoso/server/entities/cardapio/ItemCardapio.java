package com.preguicoso.server.entities.cardapio;

import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.shared.entities.cardapio.CategoriaBean;
import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;

@Entity
public class ItemCardapio {
	@Id
	Long id;
	Long estabelecimentoId;
	int numero;
	String categoria;
	String nome;
	String tipo;
	Boolean disponivel;
	String descricao;
	Long preco;
	Date dataRegistro;
	Date ultimaAtualizacao;
	Long idModelo;

	String observacao;
	int quantidade;

	public ItemCardapio() {
		this.nome = "";
		this.tipo = "";
		this.disponivel = false;
		this.descricao = "";
		this.preco = (long) 0;
	}

	public Long getEstabelecimentoId() {
		return estabelecimentoId;
	}

	public void setEstabelecimentoId(Long estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
	}

	public ItemCardapio(ItemCardapioBean ib) {
		this.nome = ib.getNome();
		this.tipo = ib.getTipo();
		this.disponivel = ib.isDisponivel();
		this.descricao = ib.getDescricao();
		this.preco = ib.getPreco();
		this.estabelecimentoId = ib.getEstabelecimentoBean();
		this.categoria = ib.getCategoriaBean().getNome();
		this.dataRegistro = ib.getDataRegistro();
		this.id = ib.getId();
		this.idModelo = ib.getIdModelo();
		this.numero = ib.getNumero();
		this.observacao = ib.getObservacao();
		this.quantidade = ib.getQuantidade();
		this.ultimaAtualizacao = ib.getUltimaAtualizacao();
	}

	public ItemCardapioBean toBean() {
		ItemCardapioBean bean = new ItemCardapioBean();
		bean.setId(this.id);
		bean.setDescricao(this.descricao);
		bean.setNome(this.nome);
		bean.setNumero(this.numero);
		bean.setPreco(this.preco);
		bean.setTipo(this.tipo);
		bean.setCategoriaBean(new CategoriaBean(getCategoria()));
		bean.setEstabelecimentoBean(this.estabelecimentoId);
		bean.setDataRegistro(this.dataRegistro);
		bean.setUltimaAtualizacao(this.ultimaAtualizacao);
		bean.setObservacao(this.observacao);
		bean.setQuantidade(this.quantidade);
		bean.setIdModelo(this.idModelo);
		return bean;
	}

	public Long getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Long idModelo) {
		this.idModelo = idModelo;
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

	public Long getPreco() {
		return preco;
	}

	public void setPreco(Long preco) {
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
