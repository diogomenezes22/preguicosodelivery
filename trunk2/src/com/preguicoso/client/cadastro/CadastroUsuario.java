package com.preguicoso.client.cadastro;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CadastroUsuario extends Composite {

	private static CadastroUsuarioUiBinder uiBinder = GWT
			.create(CadastroUsuarioUiBinder.class);
	@UiField
	Label cardapio;
	@UiField
	Label nome;

	interface CadastroUsuarioUiBinder extends UiBinder<Widget, CadastroUsuario> {
	}

	public CadastroUsuario() {
		initWidget(uiBinder.createAndBindUi(this));
		nome.setText("äbraao");
	}

}
