package com.preguicoso.client.backend.pedidos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FecharBalanco extends Composite {

	private static FecharBalancoUiBinder uiBinder = GWT
			.create(FecharBalancoUiBinder.class);

	interface FecharBalancoUiBinder extends UiBinder<Widget, FecharBalanco> {
	}

	public FecharBalanco() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
