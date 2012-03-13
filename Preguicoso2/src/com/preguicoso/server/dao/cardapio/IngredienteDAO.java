package com.preguicoso.server.dao.cardapio;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.cardapio.Ingrediente;
import com.preguicoso.shared.entities.cardapio.IngredienteBean;

public class IngredienteDAO extends DAOBase {
	static {
		ObjectifyService.register(Ingrediente.class);
	}

	public void create(Ingrediente i) {
		this.ofy().put(i);
	}

	public Ingrediente retrieve(Long id) {
		return this.ofy().query(Ingrediente.class).filter("id", id).get();
	}

	public void update(Ingrediente i) {
		this.ofy().put(i);
	}

	public void delete(Ingrediente i) {
		this.ofy().delete(i);
	}

	public List<IngredienteBean> listByIdEstabelecimento(Long idEstabelecimento) {
		List<IngredienteBean> lista = new ArrayList<IngredienteBean>();
		for (Ingrediente i : this.ofy().query(Ingrediente.class)
				.filter("idEstabelecimento", idEstabelecimento).order("nome")
				.list()) {
			lista.add(i.toBean());
		}
		return lista;
	}

	public List<Ingrediente> listAll() {
		return this.ofy().query(Ingrediente.class).list();
	}
}
