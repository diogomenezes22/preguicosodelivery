package com.preguicoso.server.dao;

import java.util.Calendar;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Estado;

public class EstadoDAO extends DAOBase {
	static {
		ObjectifyService.register(Estado.class);
	}

	public void create(Estado e) {
		if (this.retrieve(e.getNome()) != null) {
			assert false;
		}

		e.setUltimaAtualizacao(Calendar.getInstance().getTime());
		e.setDataRegistro(Calendar.getInstance().getTime());
		this.ofy().put(e);
	}

	public Estado retrieve(String nome) {
		try {
			return this.ofy().get(Estado.class, nome);
		} catch (Exception e) {
			return null;
		}
	}

	public void update(Estado e) {
	}

	public void delete(Estado e) {
		this.ofy().delete(e);
	}

	public List<Estado> listAll() {
		return this.ofy().query(Estado.class).list();
	}
}
