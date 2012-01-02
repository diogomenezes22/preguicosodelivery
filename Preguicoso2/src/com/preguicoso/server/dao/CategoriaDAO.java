package com.preguicoso.server.dao;

import java.util.Calendar;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Categoria;

public class CategoriaDAO extends DAOBase {
	static {
		ObjectifyService.register(Categoria.class);
	}

	public void create(Categoria c) {
		if (this.retrieve(c.getNome()) != null) {
			assert false;
		}

		c.setUltimaAtualizacao(Calendar.getInstance().getTime());
		c.setDataRegistro(Calendar.getInstance().getTime());
		this.ofy().put(c);	
	}

	public Categoria retrieve(String nome) {
		try {
			return this.ofy().get(Categoria.class, nome);
		} catch (Exception e) {
			return null;
		}
	}

	public void update(Categoria c) {
	}

	public void delete(Categoria c) {
		this.ofy().delete(c);
	}

	public List<Categoria> listAll() {
		return this.ofy().query(Categoria.class).list();
	}

}
