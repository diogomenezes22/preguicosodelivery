package com.preguicoso.server.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;

public class EstabelecimentoDAO extends DAOBase {
	static {
		ObjectifyService.register(Estabelecimento.class);
	}

	public void create(Estabelecimento e) {
		e.setDataRegistro(new Date());
		this.ofy().put(e);
	}

	public Estabelecimento retrieveByName(String nome) {
		return this.ofy().query(Estabelecimento.class).filter("nome", nome)
				.get();
	}

	public Estabelecimento retrieveByCnpj(String cnpj) {
		// TODO @Osman trocar por get em breve
		List<Estabelecimento> lista = this.ofy().query(Estabelecimento.class)
				.filter("cnpj", cnpj).list();
		if (!lista.isEmpty())
			return lista.get(0);
		return null;
	}

	public Estabelecimento retrieve(Long id) {
		return this.ofy().query(Estabelecimento.class).filter("id", id).get();
	}

	public void update(Estabelecimento e) {
		e.setUltimaAtualizacao(Calendar.getInstance().getTime());
		assert e.getId() != null;
		this.ofy().put(e);
	}

	public void delete(Estabelecimento e) {
		this.ofy().delete(e);
	}

	public List<Estabelecimento> listAll() {
		return this.ofy().query(Estabelecimento.class).list();
	}

	public List<EstabelecimentoBean> getListByBairro(BairroBean bb) {
		List<EstabelecimentoBean> lista = new ArrayList<EstabelecimentoBean>();
		for (Estabelecimento e : this.ofy().query(Estabelecimento.class)
				.filter("idCidade", bb.getIdCidade()).list()) {
			if (e.getIdBairroAtendimentoList().contains(bb.getId())) {
				lista.add(e.toBean());
			}
		}
		return lista;
	}
}
