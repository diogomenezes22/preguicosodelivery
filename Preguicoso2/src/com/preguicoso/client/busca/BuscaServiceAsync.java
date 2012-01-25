package com.preguicoso.client.busca;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.CidadeBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface BuscaServiceAsync {
	void getListaCidade(AsyncCallback<ArrayList<CidadeBean>> callback);

	void editarEstabelecimento(EstabelecimentoBean bean,
			AsyncCallback<Boolean> callback);

	void getEstabelecimentoPorBairro(String bairro,
			AsyncCallback<ArrayList<EstabelecimentoBean>> callback);

	void getListaEstabelecimento(
			AsyncCallback<ArrayList<EstabelecimentoBean>> callback);

	void getListaEstabelecimentoPorNome(String nome,
			AsyncCallback<ArrayList<EstabelecimentoBean>> callback);

	void getListaEstabelecimentoPorCategoria(String categoria,
			AsyncCallback<ArrayList<EstabelecimentoBean>> callback);

	void getListaEstabelecimentoByBairro(BairroBean bb,
			AsyncCallback<List<EstabelecimentoBean>> callback);
}