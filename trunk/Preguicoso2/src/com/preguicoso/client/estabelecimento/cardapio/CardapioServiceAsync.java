package com.preguicoso.client.estabelecimento.cardapio;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

public interface CardapioServiceAsync {
	void getItensCardapio(Long id,
			AsyncCallback<ArrayList<ItemCardapioBean>> callback);

	void addItem(ItemCardapioBean i, int string, String string2,
			AsyncCallback<Void> callback);

	void getCarrinho(AsyncCallback<ArrayList<ItemCardapioBean>> callback);

	void removeItem(ItemCardapioBean i, AsyncCallback<Void> callback);

	void getCategorias(Long id, AsyncCallback<ArrayList<CategoriaBean>> callback);
}