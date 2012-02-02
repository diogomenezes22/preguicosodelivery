package com.preguicoso.client.checkout;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

public interface CheckoutServiceAsync {

	void getBairrosAtendidos(EstabelecimentoBean eb,
			AsyncCallback<List<String>> callback);

	void enviarPedido(String nomeCliente, String rua, String bairro,
			String complemento, String formaPagamento,
			AsyncCallback<Void> callback);

	void getCarrinho(AsyncCallback<List<ItemCardapioBean>> callback);

}