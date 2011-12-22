package com.preguicoso.server.dao;

import java.util.Calendar;
import java.util.List;

import org.lavieri.modelutil.cep.WebServiceCep;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Bairro;
import com.preguicoso.server.entities.Cidade;
import com.preguicoso.server.entities.Estado;
import com.preguicoso.server.entities.Pais;
import com.preguicoso.server.utils.EstadoUfNome;

public class BairroDAO extends DAOBase {
	static {
		ObjectifyService.register(Bairro.class);
	}

	public Bairro create(Bairro b) {
		if (this.retrieveByCep(b.getCep()) != null) {
			assert false;
		}

		b.setDataRegistro(Calendar.getInstance().getTime());
		b.setUltimaAtualizacao(Calendar.getInstance().getTime());

		if (b.getCidade() == null || b.getNome().equals("")) {
			WebServiceCep wscCep = WebServiceCep.searchCep(b.getCep());
			if (wscCep.isCepNotFound()) {
				assert false;
			}

			PaisDAO paisDAO = new PaisDAO();
			Pais pais = (new PaisDAO()).retrieve("Brasil");
			if (pais == null) {
				paisDAO.create(new Pais("Brasil", "BR"));
				pais = paisDAO.retrieve(EstadoUfNome.getEstadoByUf(wscCep.getUf()));
			}

			EstadoDAO estadoDAO = new EstadoDAO();
			Estado estado = estadoDAO.retrieve(EstadoUfNome.getEstadoByUf(wscCep.getUf()));
			if (estado == null) {
				estadoDAO.create(new Estado(EstadoUfNome.getEstadoByUf(wscCep.getUf()), wscCep.getUf(), pais));
				estado = estadoDAO.retrieve(EstadoUfNome.getEstadoByUf(wscCep.getUf()));
			}

			CidadeDAO cidadeDAO = new CidadeDAO();
			Cidade cidade = cidadeDAO.retrieve(wscCep.getCidade());
			if (cidade == null) {
				cidadeDAO.create(new Cidade(wscCep.getCidade(), estado));
				cidade = cidadeDAO.retrieve(wscCep.getCidade());
			}

			b.setCidade(cidade);
			b.setNome(wscCep.getBairro());
		}

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
