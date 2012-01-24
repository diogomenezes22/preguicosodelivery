package com.preguicoso.server.dao;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Bairro;

public class BairroDAO extends DAOBase {
	static {
		ObjectifyService.register(Bairro.class);
	}

	public void create(Bairro b) {
		this.ofy().put(b);
	}

	public Bairro retrieveById(Long id) {
		return this.ofy().query(Bairro.class).filter("id", id).get();
	}

	public void update(Bairro b) {
		this.ofy().put(b);
	}

	public void delete(Bairro b) {
		this.ofy().delete(b);
	}

	public List<Bairro> listAll() {
		return this.ofy().query(Bairro.class).list();
	}

	public List<Bairro> listByCidade(Long idCidade) {
		List<Bairro> lista = this.ofy().query(Bairro.class)
				.filter("idCidade", idCidade).order("nome").list();
		final Collator coll = Collator.getInstance(new Locale("pt", "BR"));
		Collections.sort(lista, new Comparator<Bairro>() {

			@Override
			public int compare(Bairro o1, Bairro o2) {
				if (coll.compare(o1.getNome(), o2.getNome()) > 0) {
					return 1;
				}
				return -1;
			}
		});
		return lista;
	}
}
