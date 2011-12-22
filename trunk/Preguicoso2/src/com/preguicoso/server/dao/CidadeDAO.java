package com.preguicoso.server.dao;

import java.util.Calendar;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Cidade;

public class CidadeDAO extends DAOBase {
	static {
		ObjectifyService.register(Cidade.class);
	}

	public void create(Cidade c) {
		if (this.retrieve(c.getNome()) != null) {
			assert false;
		}

		c.setUltimaAtualizacao(Calendar.getInstance().getTime());
		c.setDataRegistro(Calendar.getInstance().getTime());
		this.ofy().put(c);
	}

	public Cidade retrieve(String nome) {
		try {
			return this.ofy().get(Cidade.class, nome);
		} catch (Exception e) {
			return null;
		}
	}

	public void update(Cidade c) {
	}

	public void delete(Cidade c) {
		this.ofy().delete(c);
	}

	public List<Cidade> listAll() {
		return this.ofy().query(Cidade.class).list();
	}
}
