package com.preguicoso.server.dao.cardapio;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.cardapio.Modelo;

public class ModeloDAO extends DAOBase {
	static {
		ObjectifyService.register(Modelo.class);
	}

	public void create(Modelo m) {
		this.ofy().put(m);
	}

	public Modelo retrieve(Long id) {
		return this.ofy().query(Modelo.class).filter("id", id).get();
	}

	public void update(Modelo m) {
		this.ofy().put(m);
	}

	public void delete(Modelo m) {
		this.ofy().delete(m);
	}

	public List<Modelo> listAll() {
		return this.ofy().query(Modelo.class).list();
	}
}
