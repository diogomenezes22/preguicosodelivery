package com.preguicoso.client.estabelecimento.filtro;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

public class CheckFiltro extends Composite {

	private static CheckFiltroUiBinder uiBinder = GWT
			.create(CheckFiltroUiBinder.class);
	@UiField
	InlineLabel nome;

	interface CheckFiltroUiBinder extends UiBinder<Widget, CheckFiltro> {
	}

	public CheckFiltro() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public CheckFiltro(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		nome.setText(firstName);
	}

}
