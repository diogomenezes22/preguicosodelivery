package com.preguicoso.client.busca;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.CidadeBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("busca")
public interface BuscaService extends RemoteService {
	ArrayList<EstabelecimentoBean> getListaEstabelecimento();

	List<EstabelecimentoBean> getListaEstabelecimentoBySession();

	ArrayList<EstabelecimentoBean> getListaEstabelecimentoPorNome(String nome);

	ArrayList<EstabelecimentoBean> getListaEstabelecimentoPorCategoria(
			String categoria);

	ArrayList<EstabelecimentoBean> getEstabelecimentoPorBairro(String bairro)
			throws IllegalArgumentException;

	ArrayList<CidadeBean> getListaCidade() throws IllegalArgumentException;

	Boolean editarEstabelecimento(EstabelecimentoBean bean);

	List<EstabelecimentoBean> getListaEstabelecimentoByBairro(BairroBean bb);

	List<EstabelecimentoBean> getListaEstabelecimentoByCidade(Long idCidade);

	List<EstabelecimentoBean> getListaEstabelecimentoBySession(String categoria);

	List<ItemCardapioBean> getListaItensByName(String nome);

	CidadeBean getCidadeBeanSession();

	BairroBean getBairroBeanSession();

	void setCidadeBeanSession(CidadeBean cb);

	void setBairroBeanSession(BairroBean bb);
}