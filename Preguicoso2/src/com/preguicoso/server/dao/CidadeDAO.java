package com.preguicoso.server.dao;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Cidade;

public class CidadeDAO extends DAOBase {
	static {
		ObjectifyService.register(Cidade.class);
	}

	public void create(Cidade c) {
		this.ofy().put(c);
	}

	public Cidade retrieveById(Long id) {
		return this.ofy().query(Cidade.class).filter("id", id).get();
	}

	public void update(Cidade c) {
		this.ofy().put(c);
	}

	public void delete(Cidade c) {
		this.ofy().delete(c);
	}

	public List<Cidade> listAll() {
		return this.ofy().query(Cidade.class).list();
	}
}
