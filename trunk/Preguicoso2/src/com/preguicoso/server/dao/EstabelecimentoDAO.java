package com.preguicoso.server.dao;

import java.util.Calendar;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Estabelecimento;

public class EstabelecimentoDAO extends DAOBase {
	static {
		ObjectifyService.register(Estabelecimento.class);
	}

	public void create(Estabelecimento e) {

		if (this.retrieveByCnpj(e.getCnpj()) != null) {
			Estabelecimento est = this.retrieveByCnpj(e.getCnpj());
			est.setAreaAtendimento(e.getAreaAtendimento());
			est.setCategoria(e.getCategoria());
			est.setDataRegistro(e.getDataRegistro());
			est.setEndereco(e.getEndereco());
			est.setLogoURL(e.getLogoURL());
			est.setNome(e.getNome());
			est.setRazaoSocial(e.getRazaoSocial());
			est.setUltimaAtualizacao(e.getUltimaAtualizacao());
			e = est;
		}
		e.setUltimaAtualizacao(Calendar.getInstance().getTime());
		e.setDataRegistro(Calendar.getInstance().getTime());
		this.ofy().put(e);
	}

	public Estabelecimento retrieveByName(String estabelecimento) {
		List<Estabelecimento> e = this.ofy().query(Estabelecimento.class)
				.filter("nome", estabelecimento).list();
		if (e.isEmpty()) {
			return null;
		}
		return e.get(0);
	}

	public Estabelecimento retrieveByCnpj(String cnpj) {
		List<Estabelecimento> e = this.ofy().query(Estabelecimento.class)
				.list();
		for (Estabelecimento estabelecimento : e) {
			if (estabelecimento.getCnpj().equals(cnpj))
				return estabelecimento;
		}
		return null;
	}

	public Estabelecimento retrieve(Long id) {
		try {
			return this.ofy().get(Estabelecimento.class, id);
		} catch (Exception e) {
			return null;
		}
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
}
