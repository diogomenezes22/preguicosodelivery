package com.preguicoso.client.busca;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.CidadeBean;
import com.preguicoso.shared.entities.EnderecoBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("busca")
public interface BuscaService extends RemoteService {
	ArrayList<EstabelecimentoBean> getListaEstabelecimento();

	ArrayList<EstabelecimentoBean> getListaEstabelecimentoPorNome(String nome);

	ArrayList<EstabelecimentoBean> getListaEstabelecimentoPorCategoria(String categoria);

	ArrayList<EstabelecimentoBean> getEstabelecimentoPorBairro(String bairro)
			throws IllegalArgumentException;

	ArrayList<CidadeBean> getListaCidade() throws IllegalArgumentException;

	EnderecoBean getEnderecoByCep(String cep);

	Boolean editarEstabelecimento(EstabelecimentoBean bean);
}