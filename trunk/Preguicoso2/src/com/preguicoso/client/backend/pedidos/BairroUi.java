package com.preguicoso.client.backend.pedidos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

public class BairroUi extends Composite {

	private static BairroUiUiBinder uiBinder = GWT
			.create(BairroUiUiBinder.class);

	@UiField
	InlineLabel bairro;
	@UiField
	HTMLPanel text;

	interface BairroUiUiBinder extends UiBinder<Widget, BairroUi> {
	}

	public BairroUi() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public BairroUi(String Bairro) {
		initWidget(uiBinder.createAndBindUi(this));
		bairro.setText(Bairro);
	}

}
