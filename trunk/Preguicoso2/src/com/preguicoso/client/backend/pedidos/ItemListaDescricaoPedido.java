package com.preguicoso.client.backend.pedidos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ItemListaDescricaoPedido extends Composite  {

	private static itemListaDescricaoPedidoUiBinder uiBinder = GWT
			.create(itemListaDescricaoPedidoUiBinder.class);

	interface itemListaDescricaoPedidoUiBinder extends
			UiBinder<Widget, ItemListaDescricaoPedido> {
	}

	public ItemListaDescricaoPedido() {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
