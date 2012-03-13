package com.preguicoso.client.backend.cardapio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class EditarGrupos extends Composite {

	private static EditarGruposUiBinder uiBinder = GWT
			.create(EditarGruposUiBinder.class);

	interface EditarGruposUiBinder extends UiBinder<Widget, EditarGrupos> {
	}

	public EditarGrupos() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
