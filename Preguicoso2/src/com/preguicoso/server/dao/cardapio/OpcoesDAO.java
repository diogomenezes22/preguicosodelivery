package com.preguicoso.server.dao.cardapio;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.cardapio.Opcoes;
import com.preguicoso.shared.entities.cardapio.OpcoesBean;

public class OpcoesDAO extends DAOBase {
	static {
		ObjectifyService.register(Opcoes.class);
	}

	public void create(Opcoes o) {
		this.ofy().put(o);
	}

	public Opcoes retrieve(Long id) {
		return this.ofy().query(Opcoes.class).filter("id", id).get();
	}

	public void update(Opcoes o) {
		this.ofy().put(o);
	}

	public void delete(Opcoes o) {
		this.ofy().delete(o);
	}

	public List<OpcoesBean> listByIdEstabelecimento(Long idEstabelecimento) {
		List<OpcoesBean> lista = new ArrayList<OpcoesBean>();
		for (Opcoes o : this.ofy().query(Opcoes.class)
				.filter("idEstabelecimento", idEstabelecimento).order("nome")
				.list()) {
			lista.add(o.toBean());
		}
		return lista;
	}

	public List<Opcoes> listAll() {
		return this.ofy().query(Opcoes.class).list();
	}
}
