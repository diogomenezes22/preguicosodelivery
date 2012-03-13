package com.preguicoso.client.estabelecimento.cardapio;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.CidadeBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.cardapio.CategoriaBean;
import com.preguicoso.shared.entities.cardapio.IngredienteBean;
import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;
import com.preguicoso.shared.entities.cardapio.OpcoesBean;

public interface CardapioServiceAsync {
	void getItensCardapio(Long id,
			AsyncCallback<ArrayList<ItemCardapioBean>> callback);

	void addItem(ItemCardapioBean i, int quantidade, String observacao,
			AsyncCallback<Void> callback);

	void getCarrinho(AsyncCallback<ArrayList<ItemCardapioBean>> callback);

	void carrinhoClean(AsyncCallback<Void> callback);

	void removeItem(ItemCardapioBean i, AsyncCallback<Void> callback);

	void getCategorias(Long id, AsyncCallback<ArrayList<CategoriaBean>> callback);

	void getCidade(Long idEstabelecimento, AsyncCallback<CidadeBean> callback);

	void setCidade(Long idEstabelecimento, CidadeBean cidadeBean,
			AsyncCallback<Void> callback);

	void getCidadesList(AsyncCallback<List<CidadeBean>> callback);

	void getBairros(Long idCidade, AsyncCallback<List<BairroBean>> callback);

	void salvarListaBairros(EstabelecimentoBean estabelecimentoBean,
			List<String> listaBairros, AsyncCallback<Void> callback);

	void updateEstabelecimento(EstabelecimentoBean eb,
			List<Long> idBairrosAtendidos, AsyncCallback<Void> callback);

	void criarCategoriaOpcao(String categoria, Long idEstabelecimento,
			AsyncCallback<OpcoesBean> callback);

	void getListaOpcoes(Long idEstabelecimento,
			AsyncCallback<List<OpcoesBean>> callback);

	void updateOpcoes(OpcoesBean ob, AsyncCallback<Void> callback);

	void removeOpcoes(OpcoesBean ob, AsyncCallback<Void> callback);

	void criarIngrediente(String nome, Long preco, Long idEstabelecimento,
			AsyncCallback<IngredienteBean> callback);

	void getListaIngredientes(Long idEstabelecimento,
			AsyncCallback<List<IngredienteBean>> callback);

	void removeIngrediente(IngredienteBean ib, AsyncCallback<Void> callback);

}
