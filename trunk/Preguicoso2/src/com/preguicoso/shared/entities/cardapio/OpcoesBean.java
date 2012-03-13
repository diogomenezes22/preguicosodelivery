package com.preguicoso.shared.entities.cardapio;

import java.io.Serializable;
import java.util.List;

public class OpcoesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2704770996212033062L;
	private Long id;
	private Long idEstabelecimento;
	private String nome;
	private List<String> listaOpcoes;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((idEstabelecimento == null) ? 0 : idEstabelecimento
						.hashCode());
		result = prime * result
				+ ((listaOpcoes == null) ? 0 : listaOpcoes.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpcoesBean other = (OpcoesBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idEstabelecimento == null) {
			if (other.idEstabelecimento != null)
				return false;
		} else if (!idEstabelecimento.equals(other.idEstabelecimento))
			return false;
		if (listaOpcoes == null) {
			if (other.listaOpcoes != null)
				return false;
		} else if (!listaOpcoes.equals(other.listaOpcoes))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
