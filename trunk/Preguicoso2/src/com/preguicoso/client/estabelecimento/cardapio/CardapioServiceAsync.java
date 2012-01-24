package com.preguicoso.client.estabelecimento.cardapio;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.CategoriaBean;
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

	void getEnderecoByCep(String cep, AsyncCallback<String[]> callback);

	void getBairrosNome(Long idCidade, AsyncCallback<List<String>> callback);

}
