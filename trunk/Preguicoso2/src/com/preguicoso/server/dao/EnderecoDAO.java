package com.preguicoso.server.dao;

import java.util.Calendar;
import java.util.List;

import org.lavieri.modelutil.cep.WebServiceCep;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Endereco;

public class EnderecoDAO extends DAOBase {
	static {
		ObjectifyService.register(Endereco.class);
	}

	public Endereco create(Endereco e) {
		e.setUltimaAtualizacao(Calendar.getInstance().getTime());
		e.setDataRegistro(Calendar.getInstance().getTime());

		if (e.getRua().equals("") && e.getBairro() != null) {
			WebServiceCep wscCep = WebServiceCep.searchCep(e.getBairro().getCep());
			if (wscCep.isCepNotFound()) {
				assert false;
			}
			e.setRua(wscCep.getLogradouroFull());
		}

		this.ofy().put(e);
		assert e.getId() != null;

		return e;
	}

	public Endereco retrieve(Long id) {
		try {
			return this.ofy().get(Endereco.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public Endereco retrieveByCep(String cep) {
		List<Endereco> addressList = this.ofy().query(Endereco.class).filter("cepBairro", cep).list();
		if(addressList.isEmpty()) {
			return null;
		}
		return addressList.get(0);
	}

	public Endereco update(Endereco e) {
		this.ofy().put(e);
		return e;
	}

	public void delete(Endereco e) {
		this.ofy().delete(e);
	}

	public List<Endereco> listAll() {
		return this.ofy().query(Endereco.class).list();
	}
}
