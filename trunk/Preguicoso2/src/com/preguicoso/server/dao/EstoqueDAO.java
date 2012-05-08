package com.preguicoso.server.dao;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.Estoque;

public class EstoqueDAO extends DAOBase {
	static {
		ObjectifyService.register(Estoque.class);
	}

	public void create(Estoque b) {
		this.ofy().put(b);
	}

	public Estoque retrieveById(Long id) {
		return this.ofy().query(Estoque.class).filter("id", id).get();
	}

	public void update(Estoque b) {
		this.ofy().put(b);
	}

	public void delete(Estoque b) {
		this.ofy().delete(b);
	}

	public List<Estoque> listAll() {
		return this.ofy().query(Estoque.class).list();
	}

	public List<Estoque> getEstoquesByName(Long idCidade, List<String> names) {
		return this.ofy().query(Estoque.class).filter("idCidade", idCidade)
				.filter("nome in", names).list();
	}

	public List<Estoque> listByEstabelecimento(Long idCidade) {
		List<Estoque> lista = this.ofy().query(Estoque.class)
				.filter("idEstabelecimento", idCidade).order("nome").list();
		final Collator coll = Collator.getInstance(new Locale("pt", "BR"));
		Collections.sort(lista, new Comparator<Estoque>() {

			@Override
			public int compare(Estoque o1, Estoque o2) {
				if (coll.compare(o1.getIdEstabelecimento(), o2.getIdEstabelecimento()) > 0) {
					return 1;
				}
				return -1;
			}
		});
		return lista;
	}

	
}
