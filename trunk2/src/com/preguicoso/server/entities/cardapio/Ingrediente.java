package com.preguicoso.server.entities.cardapio;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.entities.cardapio.IngredienteBean;

@Entity
public class Ingrediente {

	@Id
	Long id;
	Long idEstabelecimento;
	Long preco;
	String nome;

	public Ingrediente() {

	}

	public Ingrediente(IngredienteBean ib) {
		this.setId(ib.getId());
		this.setIdEstabelecimento(ib.getIdEstabelecimento());
		this.setNome(ib.getNome());
		this.setPreco(ib.getPreco());
	}

	public IngredienteBean toBean() {
		IngredienteBean ib = new IngredienteBean();
		ib.setId(id);
		ib.setNome(nome);
		ib.setPreco(preco);
		ib.setIdEstabelecimento(idEstabelecimento);
		return ib;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPreco() {
		return preco;
	}

	public void setPreco(Long preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

}
