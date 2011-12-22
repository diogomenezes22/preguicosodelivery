package com.preguicoso.server.dao;

import java.util.Calendar;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Pais;

public class PaisDAO extends DAOBase {
	static {
		ObjectifyService.register(Pais.class);
	}

	public void create(Pais p) {
		if (this.retrieve(p.getNome()) != null) {
			assert false;
		}

		p.setUltimaAtualizacao(Calendar.getInstance().getTime());
		p.setDataRegistro(Calendar.getInstance().getTime());
		this.ofy().put(p);
	}

	public Pais retrieve(String nome) {
		try {
			return this.ofy().get(Pais.class, nome);
		} catch (Exception e) {
			return null;
		}
	}

	public void update(Pais p) {
	}

	public void delete(Pais p) {
		this.ofy().delete(p);
	}

	public List<Pais> listAll() {
		return this.ofy().query(Pais.class).list();
	}
}
