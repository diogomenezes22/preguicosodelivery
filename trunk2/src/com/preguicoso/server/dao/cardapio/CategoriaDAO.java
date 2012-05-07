package com.preguicoso.server.dao.cardapio;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.cardapio.Categoria;

public class CategoriaDAO extends DAOBase {
	static {
		ObjectifyService.register(Categoria.class);
	}

	public void create(Categoria c) {
		this.ofy().put(c);
	}

	public Categoria retrieve(Long id) {
		return this.ofy().query(Categoria.class).filter("id", id).get();
	}

	public void update(Categoria c) {
		this.ofy().put(c);
	}

	public void delete(Categoria c) {
		this.ofy().delete(c);
	}

	public List<Categoria> listAll() {
		return this.ofy().query(Categoria.class).list();
	}

}
