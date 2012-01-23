package com.preguicoso.server.dao;

import java.util.Calendar;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Bairro;

public class BairroDAO extends DAOBase {
	static {
		ObjectifyService.register(Bairro.class);
	}

	public Bairro create(Bairro b) {
		b.setDataRegistro(Calendar.getInstance().getTime());
		b.setUltimaAtualizacao(Calendar.getInstance().getTime());

		this.ofy().put(b);
		return b;
	}

	public Bairro retrieveByName(String nome) {
		List<Bairro> b = this.ofy().query(Bairro.class).filter("nome", nome).list();
		if (b.isEmpty()) {
			return null;
		}
		return b.get(0);
	}
	public Bairro retrieveByCep(String cep) {
		try {
			return this.ofy().get(Bairro.class, cep);
		} catch (Exception e) {
			return null;
		}
	}

	public void update(Bairro b) {
		b.setUltimaAtualizacao(Calendar.getInstance().getTime());
	}

	public void delete(Bairro b) {
		this.ofy().delete(b);
	}

	public List<Bairro> listAll() {
		return this.ofy().query(Bairro.class).list();
	}
}
