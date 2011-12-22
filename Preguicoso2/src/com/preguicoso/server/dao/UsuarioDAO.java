package com.preguicoso.server.dao;

import java.util.Calendar;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Cidade;
import com.preguicoso.server.entities.Usuario;

public class UsuarioDAO extends DAOBase {
	static {
		ObjectifyService.register(Usuario.class);
	}

	public void create(Usuario c) {
		if (this.retrieve(c.getEmail()) != null) {
			assert false;
		}

		c.setUltimaAtualizacao(Calendar.getInstance().getTime());
		c.setDataRegistro(Calendar.getInstance().getTime());
		this.ofy().put(c);
	}

	public Usuario retrieve(String email) {
		try {
			return this.ofy().get(Usuario.class, email);
		} catch (Exception e) {
			return null;
		}
	}

	public void delete(Cidade c) {
		this.ofy().delete(c);
	}

	public List<Usuario> listAll() {
		return this.ofy().query(Usuario.class).list();
	}

	public void update(Usuario e) {
		e.setUltimaAtualizacao(Calendar.getInstance().getTime());
		Usuario b = this.retrieve(e.getEmail());
		b.setCEP(e.getCEP());
		b.setCadastrado(e.isCadastrado());
		this.ofy().put(b);
	}
}
