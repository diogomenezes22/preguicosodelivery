package com.preguicoso.server.listar;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.listar.ListaService;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.shared.entities.EstabelecimentoBean;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ListaServiceImpl extends RemoteServiceServlet implements
		ListaService {

	@Override
	public ArrayList<EstabelecimentoBean> getListaEstabelecimento() {
		/*
		 * Estabelecimento novo = new Estabelecimento("AbraaoViad�o", "Abra�o",
		 * "123132123"); (new EstabelecimentoDAO()).create(novo);
		 */
		ArrayList<EstabelecimentoBean> lista = new ArrayList<EstabelecimentoBean>();

		for (Estabelecimento estabelecimento : (new EstabelecimentoDAO())
				.listAll()) {
			lista.add(estabelecimento.toBean());
		}

		return lista;
	}

	@Override
	public ArrayList<EstabelecimentoBean> getEstabelecimentoPorCep(String cep)
			throws IllegalArgumentException {

		ArrayList<EstabelecimentoBean> list = new ArrayList<EstabelecimentoBean>();

		return list;
	}
}
