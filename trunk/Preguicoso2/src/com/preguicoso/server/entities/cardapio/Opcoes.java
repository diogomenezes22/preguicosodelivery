package com.preguicoso.server.entities.cardapio;

import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.entities.cardapio.OpcoesBean;

@Entity
public class Opcoes {

	@Id
	Long id;
	Long idEstabelecimento;
	String nome;
	List<String> listaOpcoes;

	public Opcoes() {

	}

	public Opcoes(OpcoesBean ob) {
		this.setId(ob.getId());
		this.setNome(ob.getNome());
		this.setListaOpcoes(ob.getListaOpcoes());
		this.setIdEstabelecimento(ob.getIdEstabelecimento());
	}

	public OpcoesBean toBean() {
		OpcoesBean ob = new OpcoesBean();
		ob.setId(id);
		ob.setNome(nome);
		ob.setListaOpcoes(listaOpcoes);
		ob.setIdEstabelecimento(idEstabelecimento);
		return ob;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getListaOpcoes() {
		return listaOpcoes;
	}

	public void setListaOpcoes(List<String> listaOpcoes) {
		this.listaOpcoes = listaOpcoes;
	}

	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

}
