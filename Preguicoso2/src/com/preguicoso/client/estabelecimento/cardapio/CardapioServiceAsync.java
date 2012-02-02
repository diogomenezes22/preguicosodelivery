package com.preguicoso.client.estabelecimento.cardapio;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.CidadeBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

public interface CardapioServiceAsync {
	void getItensCardapio(Long id,
			AsyncCallback<ArrayList<ItemCardapioBean>> callback);

	void addItem(ItemCardapioBean i, int quantidade, String observacao,
			AsyncCallback<Void> callback);

	void getCarrinho(AsyncCallback<ArrayList<ItemCardapioBean>> callback);

	void carrinhoClean(AsyncCallback<Void> callback);

	void removeItem(ItemCardapioBean i, AsyncCallback<Void> callback);

	void getCategorias(Long id, AsyncCallback<ArrayList<CategoriaBean>> callback);

	void enviarPedido(String nomeCliente, String rua, String bairro,
			String complemento, String formaPagamento,
			AsyncCallback<Void> callback);

	void getCidade(Long idEstabelecimento, AsyncCallback<CidadeBean> callback);

	void setCidade(Long idEstabelecimento, CidadeBean cidadeBean,
			AsyncCallback<Void> callback);

	void getCidadesList(AsyncCallback<List<CidadeBean>> callback);

	void getBairros(Long idCidade, AsyncCallback<List<BairroBean>> callback);

	void salvarListaBairros(EstabelecimentoBean estabelecimentoBean,
			List<String> listaBairros, AsyncCallback<Void> callback);

	void updateEstabelecimento(EstabelecimentoBean eb,
			List<Long> idBairrosAtendidos, AsyncCallback<Void> callback);

}
