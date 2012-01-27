package com.preguicoso.client.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class StatusPedido extends Composite {

	private static StatusPedidoUiBinder uiBinder = GWT
			.create(StatusPedidoUiBinder.class);

	interface StatusPedidoUiBinder extends UiBinder<Widget, StatusPedido> {
	}

	public StatusPedido() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
