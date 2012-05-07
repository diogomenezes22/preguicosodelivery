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

	void getListaEstabelecimentoByCidade(Long idCidade,
			AsyncCallback<List<EstabelecimentoBean>> callback);

	/**
	 * <h3>Carrega lista de restaurantes da sessão</h3>
	 * <ol>
	 * <li>Se não tiver cidade na session, carrega a lista de fortaleza</li>
	 * <li>Se tiver cidade e não tiver bairro, carrega a lista da cidade na
	 * session</li>
	 * <li>Se tiver bairro na session, carrega a lista pelo bairro</li>
	 * </ol>
	 * 
	 * @param callback
	 */
	void getListaEstabelecimentoBySession(
			AsyncCallback<List<EstabelecimentoBean>> callback);

	/**
	 * <h3>Carrega lista de restaurantes da sessão</h3>
	 * 
	 * @param categoria
	 *            nome da categoria buscada
	 * @param callback
	 */
	void getListaEstabelecimentoBySession(String categoria,
			AsyncCallback<List<EstabelecimentoBean>> callback);

	void getCidadeBeanSession(AsyncCallback<CidadeBean> callback);

	void getBairroBeanSession(AsyncCallback<BairroBean> callback);

	void setCidadeBeanSession(CidadeBean cb, AsyncCallback<Void> callback);

	void setBairroBeanSession(BairroBean bb, AsyncCallback<Void> callback);
}