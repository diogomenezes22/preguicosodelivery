package com.preguicoso.client.editar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Editar extends Composite {

	private static EditarUiBinder uiBinder = GWT.create(EditarUiBinder.class);

	interface EditarUiBinder extends UiBinder<Widget, Editar> {
	}

	public Editar() {
		this.initWidget(uiBinder.createAndBindUi(this));
	}
}
