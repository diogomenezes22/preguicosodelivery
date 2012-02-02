package com.preguicoso.server.dao;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Usuario;

public class UsuarioDAO extends DAOBase {
	static {
		ObjectifyService.register(Usuario.class);
	}

	public void create(Usuario u) {
		this.ofy().put(u);
	}

	public Usuario retrieve(String email) {
		return this.ofy().query(Usuario.class).filter("email", email).get();
	}

	public void update(Usuario u) {
		this.ofy().put(u);
	}

	public void delete(Usuario u) {
		this.ofy().delete(u);
	}

	public List<Usuario> listAll() {
		return this.ofy().query(Usuario.class).list();
	}

}