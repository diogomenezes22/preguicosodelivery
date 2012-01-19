package com.preguicoso.server.dao;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.UsuarioEstabelecimento;

public class UsuarioEstabelecimentoDAO extends DAOBase {
	static {
		ObjectifyService.register(UsuarioEstabelecimento.class);
	}

	public void create(UsuarioEstabelecimento user) {
		this.ofy().put(user);
	}

	public UsuarioEstabelecimento retrieve(String login) {
		try {
			return this.ofy().get(UsuarioEstabelecimento.class, login);
		} catch (Exception e) {
			return null;
		}
	}

	public List<UsuarioEstabelecimento> getAll() {
		return this.ofy().query(UsuarioEstabelecimento.class).list();
	}

	public void update(UsuarioEstabelecimento user) {
		this.ofy().put(user);
	}
}
