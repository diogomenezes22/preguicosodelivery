package com.preguicoso.client.backend.restaurante;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class EditarInformacao extends Composite {

	private static OrdemPedidosUiBinder uiBinder = GWT
			.create(OrdemPedidosUiBinder.class);

	interface OrdemPedidosUiBinder extends UiBinder<Widget, EditarInformacao> {
	}

	public EditarInformacao() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
