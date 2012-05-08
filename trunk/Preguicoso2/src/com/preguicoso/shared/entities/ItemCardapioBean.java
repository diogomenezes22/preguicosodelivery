package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;

public class ItemCardapioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1490509717013671633L;

	private Long id;
	private int numero;
	private String nome;
	private String tipo;
	private boolean disponivel;
	private String descricao;
	private Long preco;
	private CategoriaBean categoriaBean;
	private long estabelecimentobean;
	private Date dataRegistro;
	private Date ultimaAtualizacao;
	private String observacao;
	private String imagem;

	private int quantidade;

	public ItemCardapioBean() {
		this.id = null;
		this.nome = "";
		this.tipo = "";
		this.disponivel = false;
		this.descricao = "";
		this.preco = (long) 0;
		this.categoriaBean = new CategoriaBean();
	}

	public ItemCardapioBean(String nome, String tipo, boolean disponivel,
			String descricao, Long preco, CategoriaBean categoriaBean,
			Long estabelecimentoBean) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.disponivel = disponivel;
		this.descricao = descricao;
		this.preco = preco;
		this.categoriaBean = categoriaBean;
		this.estabelecimentobean = estabelecimentoBean;
	}
	public ItemCardapioBean(String nome, String tipo, boolean disponivel,
			String descricao, Long preco, CategoriaBean categoriaBean,
			Long estabelecimentoBean,String imagem) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.disponivel = disponivel;
		this.descricao = descricao;
		this.preco = preco;
		this.categoriaBean = categoriaBean;
		this.estabelecimentobean = estabelecimentoBean;
		this.imagem = imagem;
	}

	public String getImagem() {
		if(imagem == null)
			return "http://www.sosalergia.com.br/loja/images/sem_foto.gif";
		return imagem;
		
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Long getId() {
		return this.id;
	}

	public long getEstabelecimentobean() {
		return estabelecimentobean;
	}

	public void setEstabelecimentobean(long estabelecimentobean) {
		this.estabelecimentobean = estabelecimentobean;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isDisponivel() {
		return this.disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {

		return this.nome;
	}

	public String getTipo() {
		return this.tipo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public Long getPreco() {
		return preco;
	}

	public void setPreco(Long preco) {
		this.preco = preco;
	}

	public CategoriaBean getCategoriaBean() {
		return this.categoriaBean;
	}

	public void setCategoriaBean(CategoriaBean categoriaBean) {
		this.categoriaBean = categoriaBean;
	}

	public Long getEstabelecimentoBean() {
		return this.estabelecimentobean;
	}

	public void setEstabelecimentoBean(long estabelecimentoBean) {
		this.estabelecimentobean = estabelecimentoBean;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getDataRegistro() {
		return this.dataRegistro;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public Date getUltimaAtualizacao() {
		return this.ultimaAtualizacao;
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
}
